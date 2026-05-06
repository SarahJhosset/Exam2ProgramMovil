package com.ucb.primerproyecto.debug

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.ucb.primerproyecto.AndroidApp
import com.ucb.primerproyecto.backup.data.datasource.BackupFirebaseDataSourceImpl
import com.ucb.primerproyecto.translation.LocalTranslationService
import com.ucb.primerproyecto.worker.BackupWorker
import com.ucb.primerproyecto.worker.NotificationTranslationWorker
import com.ucb.primerproyecto.worker.CacheCleanupWorker
import com.ucb.primerproyecto.worker.SyncWorker
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import com.ucb.primerproyecto.dollar.domain.usecase.GetDollarListUsecase

@Composable
actual fun DebugScreen() {
    val context = LocalContext.current
    val scope   = rememberCoroutineScope()
    val getDollars: GetDollarListUsecase = koinInject()

    // Estados para mostrar resultados en pantalla
    var translationResult by remember { mutableStateOf("Sin resultados aún") }
    var backupResult      by remember { mutableStateOf("Sin resultados aún") }
    var roomResult        by remember { mutableStateOf("Sin resultados aún") }
    var cleanupResult     by remember { mutableStateOf("Sin resultados aún") }
    var syncResult        by remember { mutableStateOf("Sin resultados aún") }
    var isLoading         by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text       = "Panel de diagnóstico",
            fontSize   = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text  = "Usa estos botones para verificar cada ejercicio",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 13.sp
        )

        HorizontalDivider()

        // ── EJERCICIO 1: Traducciones ─────────────────────────────────
        SectionCard(title = "Ejercicio 1 — Traducciones") {

            // Prueba 1a: verifica que LocalTranslationService cargó strings
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick  = {
                    val locale = AndroidApp.currentLocale
                    val es = LocalTranslationService.get("notif_deposit_title", "es")
                    val en = LocalTranslationService.get("notif_deposit_title", "en")
                    translationResult = """
                        Locale del dispositivo: $locale
                        
                        notif_deposit_title:
                          ES → $es
                          EN → $en
                        
                        notif_backup_success:
                          ES → ${LocalTranslationService.get("notif_backup_success", "es")}
                          EN → ${LocalTranslationService.get("notif_backup_success", "en")}
                    """.trimIndent()
                }
            ) { Text("Verificar traducciones en memoria") }

            // Prueba 1b: simula una notificación FCM con clave de traducción
            // Dispara el NotificationTranslationWorker igual que lo haría FCM
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick  = {
                    val locale = AndroidApp.currentLocale
                    val request = OneTimeWorkRequestBuilder<NotificationTranslationWorker>()
                        .setInputData(
                            workDataOf(
                                "title_key" to "notif_deposit_title",
                                "body_key"  to "notif_deposit_body",
                                "locale"    to locale
                            )
                        )
                        .build()
                    WorkManager.getInstance(context).enqueue(request)
                    translationResult = """
                        Worker encolado.
                        
                        Locale usado: $locale
                        Clave título: notif_deposit_title
                        Clave cuerpo: notif_deposit_body
                        
                        Revisa la barra de notificaciones —
                        deberías ver la notificación en $locale
                    """.trimIndent()
                }
            ) { Text("Simular notificación FCM traducida") }

            ResultBox(translationResult)
        }

        // ── EJERCICIO 2: Backup ───────────────────────────────────────
        SectionCard(title = "Ejercicio 2 — Backup nocturno") {

            // Prueba 2a: lee Room y muestra cuántos registros hay
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick  = {
                    scope.launch {
                        isLoading  = true
                        roomResult = "Leyendo Room..."
                        try {
                            val dollars = getDollars.invoke()
                            roomResult = """
                                Room leído correctamente.
                                
                                Registros de dólares: ${dollars.size}
                                ${dollars.take(3).joinToString("\n") {
                                "  • Oficial: ${it.dollarOfficial} / Paralelo: ${it.dollarParallel}"
                            }}
                                ${if (dollars.size > 3) "  ... y ${dollars.size - 3} más" else ""}
                            """.trimIndent()
                        } catch (e: Exception) {
                            roomResult = "Error al leer Room: ${e.message}"
                        }
                        isLoading = false
                    }
                }
            ) { Text("Verificar datos en Room") }

            // Prueba 2b: sube los datos a Firebase directamente
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick  = {
                    scope.launch {
                        isLoading    = true
                        backupResult = "Conectando con Firebase..."
                        try {
                            val dollars = getDollars.invoke()
                            val source  = BackupFirebaseDataSourceImpl()
                            val ok      = source.backupDollars(dollars)
                            backupResult = if (ok) {
                                """
                                ✅ Firebase OK
                                
                                ${dollars.size} registros subidos a:
                                backups/dollars/{timestamp}
                                
                                Verifica en Firebase Console →
                                Realtime Database → backups
                                """.trimIndent()
                            } else {
                                "❌ Firebase devolvió false"
                            }
                        } catch (e: Exception) {
                            backupResult = "❌ Error Firebase: ${e.message}"
                        }
                        isLoading = false
                    }
                }
            ) { Text("Probar escritura directa en Firebase") }

            // Prueba 2c: dispara BackupWorker completo (el mismo que corre de noche)
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick  = {
                    val request = OneTimeWorkRequestBuilder<BackupWorker>().build()
                    WorkManager.getInstance(context).enqueue(request)
                    backupResult = """
                        BackupWorker encolado.
                        
                        El Worker hará:
                        1. Leer Room
                        2. Subir a Firebase
                        3. Mostrar notificación con resultado
                        
                        Revisa la barra de notificaciones.
                        Si falla, reintentará hasta 3 veces.
                        
                        También puedes ver el estado en:
                        Android Studio → App Inspection →
                        Background Task Inspector
                    """.trimIndent()
                }
            ) { Text("Ejecutar backup completo ahora") }

            if (isLoading) {
                CircularProgressIndicator()
            }

            ResultBox(backupResult)
        }

        // ── Instrucciones para Logcat ─────────────────────────────────
        SectionCard(title = "Cómo ver los logs en tiempo real") {
            Text(
                text = """
                    En Android Studio:
                    1. Abre Logcat (View → Tool Windows → Logcat)
                    2. Filtra por cada tag:
                    
                    Para traducciones:
                    tag:TranslationWorker
                    
                    Para backup:
                    tag:BackupWorker
                    
                    Para FCM:
                    tag:FirebaseService
                    
                    Para ver TODO el proceso:
                    tag:BackupWorker | tag:TranslationWorker | tag:FirebaseService
                """.trimIndent(),
                fontSize = 13.sp,
                color    = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        // ── EJERCICIO 6: Limpieza ─────────────────────────────────
        SectionCard(title = "Ejercicio 6 — Limpieza de caché") {

            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick  = {
                    scope.launch {
                        val count = getDollars.invoke().size
                        cleanupResult = "Registros actuales en Room: $count\n" +
                                "Límite en Remote Config: revisa Firebase Console"
                    }
                }
            ) { Text("Ver registros actuales") }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick  = {
                    val request = OneTimeWorkRequestBuilder<CacheCleanupWorker>().build()
                    WorkManager.getInstance(context).enqueue(request)
                    cleanupResult = "CacheCleanupWorker encolado.\n" +
                            "Revisa la barra de notificaciones.\n" +
                            "Logcat: tag:CacheCleanupWorker"
                }
            ) { Text("Ejecutar limpieza ahora") }

            ResultBox(cleanupResult)
        }

        // ── EJERCICIO 2: Sincronización dinámica ──────────────────
        SectionCard(title = "Ejercicio 2 — Intervalo dinámico") {

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick  = {
                    val request = OneTimeWorkRequestBuilder<SyncWorker>().build()
                    WorkManager.getInstance(context).enqueue(request)
                    syncResult = "SyncWorker encolado.\n\n" +
                            "Para probar el intervalo dinámico:\n" +
                            "1. Ve a Firebase Console → Remote Config\n" +
                            "2. Cambia sync_interval_minutes a 15\n" +
                            "3. Publica los cambios\n" +
                            "4. Reinicia la app\n" +
                            "5. El Worker ahora corre cada 15 min\n\n" +
                            "Logcat: tag:DynamicScheduler | tag:SyncWorker"
                }
            ) { Text("Ejecutar sincronización ahora") }

            ResultBox(syncResult)
        }
    }
}

@Composable
fun SectionCard(title: String, content: @Composable () -> Unit) {
    Card(
        modifier  = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(title, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
            HorizontalDivider()
            content()
        }
    }
}

@Composable
fun ResultBox(text: String) {
    if (text != "Sin resultados aún") {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors   = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Text(
                text     = text,
                modifier = Modifier.padding(12.dp),
                fontSize = 12.sp,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
            )
        }
    }
}
