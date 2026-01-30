package com.example.chen

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.PowerManager
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.media.AudioAttributes
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.SystemClock
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.content.pm.PackageManager
import android.content.pm.ActivityInfo
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.provider.Settings
import android.webkit.MimeTypeMap
import android.webkit.PermissionRequest
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import java.io.IOException
import java.util.Locale
import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONArray
import org.json.JSONObject
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.input.pointer.pointerInput
import com.example.chen.doushabao.DsbMessageEntity
import com.example.chen.doushabao.DsbRepository
import com.example.chen.ui.theme.ChenTheme
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import kotlin.math.roundToInt
import kotlin.math.min

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        createPomodoroNotificationChannels(this)
        loadCChatConfig(this)

        setContent {
            ChenTheme {
                AppRoot(
                    context = this@MainActivity,
                    onRequestBatteryPermission = { openBatteryOptimizationSettings() }
                )
            }
        }
    }

    private fun openBatteryOptimizationSettings() {
        // 优先尝试跳转到电池优化设置页面
        try {
            val intent = Intent().apply {
                action = android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
                data = android.net.Uri.parse("package:$packageName")
            }
            startActivity(intent)
            return
        } catch (e: Exception) {
            // 继续尝试其他方式
        }

        // 跳转到应用详情页面（更通用的方式）
        try {
            val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = android.net.Uri.parse("package:$packageName")
            }
            startActivity(intent)
        } catch (e: Exception) {
            // 如果还不行，打开系统设置主页
            try {
                startActivity(Intent(android.provider.Settings.ACTION_SETTINGS))
            } catch (e2: Exception) {
                // 静默失败
            }
        }
    }
}

@Composable
private fun BatteryPermissionDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF6B35),
                    contentColor = Color.White
                )
            ) {
                Text("去设置")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("暂不", color = Color(0xFF757575))
            }
        },
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    modifier = Modifier.size(56.dp),
                    shape = CircleShape,
                    color = Color(0xFFFFE0B2)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("🔔", style = MaterialTheme.typography.titleLarge)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "通知权限提示",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        text = {
            Column {
                Text(
                    text = "为了让倒计时结束后能收到通知，请关闭后台耗电限制：",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF5F5F5)
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        StepItem(number = "1", text = "打开 设置")
                        StepItem(number = "2", text = "进入 电池 → 后台耗电管理")
                        StepItem(number = "3", text = "找到本 App，选择 允许后台运行")
                    }
                }
            }
        }
    )
}

@Composable
private fun StepItem(number: String, text: String) {
    Row(
        modifier = Modifier.padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(24.dp),
            shape = CircleShape,
            color = Color(0xFFFF6B35)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = number,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF424242)
        )
    }
}

private enum class Screen {
    HOME,
    POMODORO,
    CCHAT,
    DOUSHA_BAO,
    IMAGE_COMPRESS,
    MEMORY_TREE,
    MARQUEE,
    API_STATS
}

private data class ToolItem(
    val title: String,
    val description: String,
    val enabled: Boolean,
    val onClick: () -> Unit
)

private enum class ChatRole {
    USER,
    ASSISTANT
}

private data class ChatMessage(
    val id: Int,
    val role: ChatRole,
    val content: String
)

private data class CChatConfig(
    val models: List<String>,
    val reasoningEffort: String,
    val disableResponseStorage: Boolean,
    val requiresOpenAiAuth: Boolean,
    val preferredAuthMethod: String,
    val baseUrl: String,
    val wireApi: String,
    val envKey: String
)

private const val CCHAT_PREFS_NAME = "cchat_prefs"
private const val CCHAT_KEY_SELECTED_MODEL = "cchat_selected_model"
private const val CCHAT_KEY_REASONING_EFFORT = "cchat_reasoning_effort"
private const val CCHAT_KEY_DISABLE_STORAGE = "cchat_disable_response_storage"
private const val CCHAT_KEY_REQUIRES_AUTH = "cchat_requires_openai_auth"
private const val CCHAT_KEY_PREFERRED_AUTH = "cchat_preferred_auth_method"
private const val CCHAT_KEY_BASE_URL = "cchat_base_url"
private const val CCHAT_KEY_WIRE_API = "cchat_wire_api"
private const val CCHAT_KEY_ENV_KEY = "cchat_env_key"
private const val CCHAT_DEFAULT_REASONING_EFFORT = "High"
private const val CCHAT_DEFAULT_DISABLE_STORAGE = true
private const val CCHAT_DEFAULT_REQUIRES_AUTH = true
private const val CCHAT_DEFAULT_AUTH_METHOD = "apikey"
private const val CCHAT_DEFAULT_BASE_URL = "https://api-vip.codex-for.me/v1"
private const val CCHAT_DEFAULT_WIRE_API = "responses"
private const val CCHAT_DEFAULT_ENV_KEY =
    "clp_3a96bd1780f4ef2837be01c30bbecbe1cf902238861b30d7d3c4b4bc17e89317"
private const val CCHAT_DEFAULT_INSTRUCTIONS = "You are a helpful assistant."
private const val CCHAT_DEFAULT_SELECTED_MODEL = "gpt-5.2-codex"
private val CCHAT_DEFAULT_MODELS = listOf("gpt-5.2-codex", "gpt-5.2")

private const val DSB_RECENT_MESSAGE_KEEP = 25
private const val DSB_COMPRESSION_TOKEN_THRESHOLD = 12000
private const val DSB_COMPRESSION_MODEL = "gpt-5.2"

private val DSB_DEFAULT_SUMMARY_JSON = """
{"version":1,"user_profile":{"facts":[],"preferences":[],"style":[]},"long_term_goals":[],"ongoing_tasks":[],"constraints":[],"critical_history":[],"last_updated":""}
""".trimIndent()

private val DSB_COMPRESSION_SYSTEM_PROMPT = """
你是“豆沙包”的长期记忆压缩引擎。输出必须是严格 JSON，不要任何 Markdown。
目标：合并旧摘要与新消息，保留长期、稳定、有复用价值的信息；删除噪声与一次性内容。
规则：
- 若新消息与旧摘要冲突，以最新用户消息为准。
- 合并重复信息，保持简洁。
- 不要编造；仅基于输入。
- 输出字段必须完整，若无内容用空数组。
- summary_json 总长度控制在约 1500 tokens 以内（尽量更短）。
JSON Schema:
{
  "version": 1,
  "user_profile": {
    "facts": [],
    "preferences": [],
    "style": []
  },
  "long_term_goals": [],
  "ongoing_tasks": [],
  "constraints": [],
  "critical_history": [],
  "last_updated": "ISO-8601"
}
""".trimIndent()

private val DSB_COMPRESSION_MUTEX = Mutex()

private enum class MarqueeDirection {
    LEFT,
    RIGHT
}

private data class TaskItem(
    val id: Int,
    val title: String,
    val durationMinutes: Int,
    val completed: Boolean
)

private enum class ImageFormat {
    JPEG,
    WEBP,
    PNG
}

private enum class CompressionMode {
    TARGET_SIZE,
    RATIO
}

private enum class CompressionStatus {
    IDLE,
    READY,
    COMPRESSED
}

private data class ImageMeta(
    val uri: Uri,
    val displayName: String,
    val sizeBytes: Long,
    val width: Int,
    val height: Int,
    val mimeType: String
)

private data class CompressionOutcome(
    val data: ByteArray,
    val metTarget: Boolean,
    val reachedQualityLimit: Boolean,
    val reachedScaleLimit: Boolean
)

private data class QualitySearchResult(
    val data: ByteArray,
    val metTarget: Boolean,
    val reachedMinQuality: Boolean
)

@Stable
private class PomodoroState {
    var selectedMode by mutableStateOf(TimerMode.COUNTDOWN)
    var activeMode by mutableStateOf(TimerMode.COUNTDOWN)
    var timerStatus by mutableStateOf(TimerStatus.IDLE)
    var pomodoroPhase by mutableStateOf(PomodoroPhase.WORK)
    var pomodoroCycleCount by mutableIntStateOf(0)
    var workMinutes by mutableIntStateOf(25)
    var shortBreakMinutes by mutableIntStateOf(5)
    var longBreakMinutes by mutableIntStateOf(15)
    var countdownMinutes by mutableIntStateOf(60)
    var sessionTotalMillis by mutableLongStateOf(60L * 60L * 1000L)
    var remainingMillis by mutableLongStateOf(60L * 60L * 1000L)
    var elapsedMillis by mutableLongStateOf(0L)
    var startTimestamp by mutableLongStateOf(0L)
    var baseRemainingMillis by mutableLongStateOf(0L)
    var baseElapsedMillis by mutableLongStateOf(0L)
    var activeTaskId by mutableIntStateOf(-1)
    var activeTaskTitle by mutableStateOf("")
    var totalFocusMillisToday by mutableLongStateOf(0L)
    var totalFocusMillisWeek by mutableLongStateOf(0L)
    var completedPomodoros by mutableIntStateOf(0)
    var completedTasks by mutableIntStateOf(0)
    var enableFinishVibrate by mutableStateOf(true)
    var enableFinishNotification by mutableStateOf(true)
    val tasks = mutableStateListOf<TaskItem>()
    var nextTaskId by mutableIntStateOf(1)
}

@Composable
private fun rememberPomodoroState(): PomodoroState {
    return remember { PomodoroState() }
}

@Composable
private fun AppRoot(
    context: Context,
    onRequestBatteryPermission: () -> Unit
) {
    var screen by rememberSaveable { mutableStateOf(Screen.HOME) }
    var showBatteryDialog by rememberSaveable { mutableStateOf(false) }
    val pomodoroState = rememberPomodoroState()
    val backStack = remember { mutableStateListOf<Screen>() }

    // 检测电池优化设置
    LaunchedEffect(Unit) {
        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        if (!powerManager.isIgnoringBatteryOptimizations(context.packageName)) {
            showBatteryDialog = true
        }
    }

    fun navigateTo(target: Screen, addToBackStack: Boolean = true) {
        if (target == screen) return
        if (addToBackStack) {
            backStack.add(screen)
        }
        screen = target
    }

    fun popBackStack() {
        if (backStack.isNotEmpty()) {
            screen = backStack.removeAt(backStack.lastIndex)
        }
    }

    PomodoroTicker(state = pomodoroState)

    BackHandler(enabled = backStack.isNotEmpty()) {
        popBackStack()
    }

    when (screen) {
        Screen.HOME -> HomeScreen(
            onCChatStart = { navigateTo(Screen.CCHAT) },
            onDoushabaoStart = { navigateTo(Screen.DOUSHA_BAO) },
            onPomodoroStart = { navigateTo(Screen.POMODORO) },
            onMarqueeStart = { navigateTo(Screen.MARQUEE) },
            onImageCompress = { navigateTo(Screen.IMAGE_COMPRESS) },
            onMemoryTreeStart = { navigateTo(Screen.MEMORY_TREE) },
            onApiStatsStart = { navigateTo(Screen.API_STATS) }
        )
        Screen.POMODORO -> PomodoroHome(
            state = pomodoroState,
            onBack = { popBackStack() }
        )
        Screen.CCHAT -> CChatScreen(
            onBack = { popBackStack() }
        )
        Screen.DOUSHA_BAO -> DoushabaoScreen(
            onBack = { popBackStack() }
        )
        Screen.MARQUEE -> MarqueeToolScreen(
            onBack = { popBackStack() }
        )
        Screen.IMAGE_COMPRESS -> ImageCompressionScreen(
            onBack = { popBackStack() }
        )
        Screen.MEMORY_TREE -> MemoryTreeScreen(
            onBack = { popBackStack() }
        )
        Screen.API_STATS -> ApiStatsScreen(
            onBack = { popBackStack() }
        )
    }

    // 显示电池优化权限对话框
    if (showBatteryDialog) {
        BatteryPermissionDialog(
            onDismiss = { showBatteryDialog = false },
            onConfirm = {
                showBatteryDialog = false
                onRequestBatteryPermission()
            }
        )
    }
}

@Composable
private fun HomeScreen(
    onCChatStart: () -> Unit,
    onDoushabaoStart: () -> Unit,
    onPomodoroStart: () -> Unit,
    onMarqueeStart: () -> Unit,
    onImageCompress: () -> Unit,
    onMemoryTreeStart: () -> Unit,
    onApiStatsStart: () -> Unit
) {
    val gradient = remember {
        Brush.verticalGradient(
            colors = listOf(Color(0xFF0F2027), Color(0xFF203A43), Color(0xFF2C5364))
        )
    }

    val tools = remember(
        onCChatStart,
        onDoushabaoStart,
        onPomodoroStart,
        onMarqueeStart,
        onImageCompress,
        onMemoryTreeStart,
        onApiStatsStart
    ) {
        listOf(
            ToolItem(
                title = "豆沙包",
                description = "温馨养成系 AI 记忆伙伴",
                enabled = true,
                onClick = onDoushabaoStart
            ),
            ToolItem(
                title = "CChat",
                description = "ChatGPT 风格的 AI 问答",
                enabled = true,
                onClick = onCChatStart
            ),
            ToolItem(
                title = "API 统计",
                description = "查询 Key 的费用与模型用量",
                enabled = true,
                onClick = onApiStatsStart
            ),
            ToolItem(
                title = "番茄时钟",
                description = "纯倒计时专注工具",
                enabled = true,
                onClick = onPomodoroStart
            ),
            ToolItem(
                title = "滚动字幕",
                description = "设置滚动一行通知",
                enabled = true,
                onClick = onMarqueeStart
            ),
            ToolItem(
                title = "图片压缩",
                description = "轻松压缩图片体积",
                enabled = true,
                onClick = onImageCompress
            ),
            ToolItem(
                title = "回忆圣诞树",
                description = "手势驱动的3D照片云圣诞树",
                enabled = true,
                onClick = onMemoryTreeStart
            ),
            ToolItem(
                title = "敬请期待",
                description = "更多工具正在准备",
                enabled = false,
                onClick = {}
            ),
            ToolItem(
                title = "工具预留",
                description = "后续功能入口",
                enabled = false,
                onClick = {}
            ),
            ToolItem(
                title = "建设中",
                description = "即将上线",
                enabled = false,
                onClick = {}
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(24.dp)
    ) {
        Text(
            text = "小白的工具箱",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "选择一个工具开始使用",
            color = Color(0xFFE0F2F1),
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(tools) { tool ->
                ToolTile(tool = tool)
            }
        }
    }
}

@Composable
private fun ToolTile(tool: ToolItem) {
    val contentAlpha = if (tool.enabled) 1f else 0.65f
    val containerColor = if (tool.enabled) Color(0xFFF1F8F7) else Color(0xFFE0E0E0)

    Surface(
        color = containerColor,
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 6.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .alpha(contentAlpha)
            .clickable(enabled = tool.enabled, onClick = tool.onClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = tool.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF004D40),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = tool.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF00695C),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun PomodoroTicker(state: PomodoroState) {
    val context = LocalContext.current.applicationContext
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    // 检查倒计时是否在后台期间已结束
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                if (state.timerStatus == TimerStatus.RUNNING && state.activeMode != TimerMode.COUNT_UP) {
                    val now = SystemClock.elapsedRealtime()
                    val delta = now - state.startTimestamp
                    val remaining = (state.baseRemainingMillis - delta).coerceAtLeast(0L)
                    if (remaining <= 0L) {
                        // 倒计时已在后台结束，立即触发通知
                        state.timerStatus = TimerStatus.FINISHED
                        state.remainingMillis = 0L
                        state.elapsedMillis = state.sessionTotalMillis
                        handleCountdownFinished(state)
                        triggerPomodoroFinishAlerts(context, state)
                    }
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    LaunchedEffect(state.timerStatus, state.activeMode, state.startTimestamp) {
        if (state.timerStatus != TimerStatus.RUNNING) return@LaunchedEffect
        while (state.timerStatus == TimerStatus.RUNNING) {
            val now = SystemClock.elapsedRealtime()
            val delta = now - state.startTimestamp
            if (state.activeMode == TimerMode.COUNT_UP) {
                state.elapsedMillis = state.baseElapsedMillis + delta
            } else {
                val remaining = (state.baseRemainingMillis - delta).coerceAtLeast(0L)
                state.remainingMillis = remaining
                state.elapsedMillis = (state.sessionTotalMillis - remaining).coerceAtLeast(0L)
                if (remaining <= 0L) {
                    state.timerStatus = TimerStatus.FINISHED
                    state.elapsedMillis = state.sessionTotalMillis
                    handleCountdownFinished(state)
                    triggerPomodoroFinishAlerts(context, state)
                    break
                }
            }
            delay(200L)
        }
    }
}

@Composable
private fun PomodoroHome(
    state: PomodoroState,
    onBack: () -> Unit
) {
    val gradient = remember {
        Brush.verticalGradient(
            colors = listOf(BookBgDeep, BookBgLight)
        )
    }
    val showPendingHint = state.timerStatus == TimerStatus.RUNNING ||
        state.timerStatus == TimerStatus.PAUSED
    val notificationStatus = rememberPomodoroNotificationStatus(POMODORO_CHANNEL_ALERT)
    val showNotificationHint = state.enableFinishNotification &&
        (!notificationStatus.notificationsAllowed || !notificationStatus.headsUpEnabled)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(WindowInsets.navigationBars.asPaddingValues())
            .padding(20.dp)
    ) {
        PomodoroTopBar(onBack = onBack)
        Spacer(modifier = Modifier.height(16.dp))
        if (showNotificationHint) {
            Surface(
                color = PomodoroPaper,
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(1.dp, PomodoroLine),
                tonalElevation = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "提醒权限检查",
                        color = PomodoroInk,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    PomodoroNotificationHints(
                        enabled = true,
                        status = notificationStatus,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
        TimerSurface(state = state)
        Spacer(modifier = Modifier.height(16.dp))
        PresetSection(state = state)
        Spacer(modifier = Modifier.height(12.dp))
        CustomTimeSection(state = state)
        if (showPendingHint) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "计时中，修改将在下一次生效",
                color = BookMuted,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        TimerControls(state = state)
    }
}

@Composable
private fun PomodoroTopBar(
    onBack: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        TextButton(onClick = onBack) {
            Text(text = "返回", color = BookInk)
        }
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "番茄时钟",
                color = BookInk,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "纯倒计时",
                color = BookMuted,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun ModeTabs(state: PomodoroState) {
    Column {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            ModeChip(
                label = "番茄",
                selected = state.selectedMode == TimerMode.POMODORO,
                onClick = { selectMode(state, TimerMode.POMODORO) }
            )
            ModeChip(
                label = "倒计时",
                selected = state.selectedMode == TimerMode.COUNTDOWN,
                onClick = { selectMode(state, TimerMode.COUNTDOWN) }
            )
            ModeChip(
                label = "计时",
                selected = state.selectedMode == TimerMode.COUNT_UP,
                onClick = { selectMode(state, TimerMode.COUNT_UP) }
            )
        }
        if (
            state.timerStatus != TimerStatus.IDLE &&
            state.activeMode != state.selectedMode
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "当前计时：${modeLabel(state.activeMode)}",
                color = PomodoroPaper,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun ModeChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val background = if (selected) PomodoroSeal else Color.Transparent
    val borderColor = if (selected) PomodoroSeal else PomodoroLine
    val textColor = if (selected) Color.White else PomodoroPaper

    Surface(
        shape = RoundedCornerShape(999.dp),
        color = background,
        border = BorderStroke(1.dp, borderColor),
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Text(
            text = label,
            color = textColor,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
private fun TimerSurface(state: PomodoroState) {
    val totalMillis = if (state.sessionTotalMillis > 0L) {
        state.sessionTotalMillis
    } else {
        state.countdownMinutes * 60L * 1000L
    }
    val displayMillis = when (state.timerStatus) {
        TimerStatus.FINISHED -> 0L
        TimerStatus.IDLE -> totalMillis
        else -> state.remainingMillis
    }
    val progress = if (totalMillis > 0L) {
        displayMillis.toFloat() / totalMillis.toFloat()
    } else {
        1f
    }
    val statusText = when (state.timerStatus) {
        TimerStatus.IDLE -> "未开始"
        TimerStatus.RUNNING -> "计时中"
        TimerStatus.PAUSED -> "已暂停"
        TimerStatus.FINISHED -> "已完成"
    }
    val sessionMinutes = if (totalMillis > 0L) {
        (totalMillis / 60000L).toInt()
    } else {
        state.countdownMinutes
    }

    val transition = rememberInfiniteTransition(label = "timerGradient")
    val drift by transition.animateFloat(
        initialValue = -0.18f,
        targetValue = 0.18f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 6200, easing = LinearEasing),
            RepeatMode.Reverse
        ),
        label = "drift"
    )
    val pulse by transition.animateFloat(
        initialValue = 0.96f,
        targetValue = 1.02f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 3200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        ),
        label = "pulse"
    )

    val configuration = LocalConfiguration.current
    val availableWidth = (configuration.screenWidthDp.dp - 40.dp).coerceAtLeast(160.dp)
    val circleSize = minOf(availableWidth, 240.dp)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            val sizePx = with(LocalDensity.current) { circleSize.toPx() }
            val center = Offset(
                sizePx / 2f + drift * sizePx,
                sizePx / 2f - drift * sizePx
            )
            val gradient = Brush.radialGradient(
                colors = listOf(
                    BookSeal.copy(alpha = 0.45f),
                    Color(0xFFB7774B).copy(alpha = 0.35f),
                    BookMist.copy(alpha = 0.18f),
                    Color.Transparent
                ),
                center = center,
                radius = sizePx * 0.75f
            )
            Box(
                modifier = Modifier
                    .size(circleSize)
                    .graphicsLayer {
                        scaleX = pulse
                        scaleY = pulse
                    }
                    .clip(CircleShape)
                    .background(gradient)
                    .border(1.dp, BookLine.copy(alpha = 0.35f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.matchParentSize()) {
                    val arcStroke = Stroke(width = size.minDimension * 0.04f)
                    drawArc(
                        color = BookSeal.copy(alpha = 0.55f),
                        startAngle = -90f,
                        sweepAngle = 360f * progress.coerceIn(0f, 1f),
                        useCenter = false,
                        style = arcStroke
                    )
                    drawCircle(
                        color = BookLine.copy(alpha = 0.2f),
                        style = Stroke(width = size.minDimension * 0.02f)
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = formatTimer(displayMillis),
                        color = BookInk,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif
                    )
                    Text(
                        text = statusText,
                        color = BookMuted,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Serif
                    )
                }
            }
        }
        Text(
            text = "目标 ${sessionMinutes} 分钟",
            color = BookMuted,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun PresetSection(state: PomodoroState) {
    val presets = listOf(60, 30, 15, 5)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (minutes in presets) {
                PresetChip(
                    label = "${minutes}分钟",
                    selected = state.countdownMinutes == minutes,
                    onClick = { applyCountdownMinutes(state, minutes) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun PresetChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(999.dp)
    val background = if (selected) BookSeal else BookMist
    val labelColor = if (selected) Color.White else BookInk

    Box(
        modifier = modifier
            .height(40.dp)
            .clip(shape)
            .background(background)
            .then(
                if (selected) {
                    Modifier
                } else {
                    Modifier.border(1.dp, BookLine, shape)
                }
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 6.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = labelColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Clip,
            softWrap = false,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun CustomTimeSection(state: PomodoroState) {
    var input by remember { mutableStateOf(state.countdownMinutes.toString()) }
    var inputError by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(state.countdownMinutes) {
        input = state.countdownMinutes.toString()
    }

    val hint = inputError ?: "支持 1-240 分钟"

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "自定义时长",
                color = BookInk,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = hint,
                color = if (inputError == null) BookMuted else BookSeal,
                fontSize = 12.sp
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = input,
                onValueChange = {
                    input = it
                    inputError = null
                },
                label = { Text(text = "分钟") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 48.dp, max = 160.dp)
            )
            Button(
                onClick = {
                    val minutes = input.trim().toIntOrNull()
                    val safeMinutes = minutes?.coerceIn(1, 240)
                    if (safeMinutes == null) {
                        inputError = "请输入有效分钟数"
                    } else {
                        inputError = null
                        applyCountdownMinutes(state, safeMinutes)
                        input = safeMinutes.toString()
                    }
                },
                modifier = Modifier.height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BookSeal,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "应用",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun TimerControls(state: PomodoroState) {
    val context = LocalContext.current
    val primaryLabel = when (state.timerStatus) {
        TimerStatus.IDLE -> "开始"
        TimerStatus.RUNNING -> "暂停"
        TimerStatus.PAUSED -> "继续"
        TimerStatus.FINISHED -> "重新开始"
    }
    val secondaryEnabled = state.timerStatus != TimerStatus.IDLE

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = {
                    when (state.timerStatus) {
                        TimerStatus.IDLE -> startNewSession(context, state)
                        TimerStatus.RUNNING -> pauseSession(context, state)
                        TimerStatus.PAUSED -> resumeSession(context, state)
                        TimerStatus.FINISHED -> startNewSession(context, state)
                    }
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BookSeal,
                    contentColor = Color.White
                )
            ) {
                Text(text = primaryLabel)
            }
            Button(
                onClick = { resetSession(context, state) },
                enabled = secondaryEnabled,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BookCard,
                    contentColor = BookInk,
                    disabledContainerColor = BookLine,
                    disabledContentColor = BookMuted
                )
            ) {
                Text(text = "重置")
            }
        }
    }
}

@Composable
private fun TaskSection(state: PomodoroState, isBusy: Boolean) {
    val context = LocalContext.current
    var taskTitle by remember { mutableStateOf("") }
    var taskMinutesInput by remember { mutableStateOf(state.workMinutes.toString()) }

    Surface(
        color = PomodoroPaper,
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, PomodoroLine),
        tonalElevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "任务列表",
                color = PomodoroInk,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = taskTitle,
                    onValueChange = { taskTitle = it },
                    label = { Text(text = "任务") },
                    singleLine = true,
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = taskMinutesInput,
                    onValueChange = { taskMinutesInput = it },
                    label = { Text(text = "分钟") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.width(96.dp)
                )
                Button(
                    onClick = {
                        val minutes = taskMinutesInput.toIntOrNull() ?: 0
                        if (taskTitle.isNotBlank() && minutes > 0) {
                            state.tasks.add(
                                TaskItem(
                                    id = state.nextTaskId++,
                                    title = taskTitle.trim(),
                                    durationMinutes = minutes,
                                    completed = false
                                )
                            )
                            taskTitle = ""
                            taskMinutesInput = state.workMinutes.toString()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PomodoroSeal,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "添加")
                }
            }
            if (state.selectedMode == TimerMode.COUNTDOWN) {
                CountdownConfigRow(state = state)
            }
            if (state.tasks.isEmpty()) {
                Text(
                    text = "还没有任务，添加一个开始专注。",
                    color = PomodoroMuted,
                    fontSize = 14.sp
                )
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(state.tasks, key = { it.id }) { task ->
                        TaskCard(
                            task = task,
                            isActive = state.activeTaskId == task.id,
                            isBusy = isBusy,
                            onStart = { startNewSession(context, state, task) },
                            onComplete = { completeTaskById(state, task.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CountdownConfigRow(state: PomodoroState) {
    var countdownInput by remember { mutableStateOf(state.countdownMinutes.toString()) }
    val canApply = state.timerStatus == TimerStatus.IDLE || state.timerStatus == TimerStatus.FINISHED

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "自定义倒计时",
            color = PomodoroMuted,
            fontSize = 13.sp
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = countdownInput,
                onValueChange = { countdownInput = it },
                label = { Text(text = "分钟") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.width(120.dp)
            )
            Button(
                onClick = {
                    val minutes = countdownInput.toIntOrNull() ?: 0
                    if (minutes > 0) {
                        state.countdownMinutes = minutes
                        if (canApply) {
                            syncIdleTimer(state)
                        }
                    }
                },
                enabled = canApply,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PomodoroInk,
                    contentColor = PomodoroPaper,
                    disabledContainerColor = PomodoroLine,
                    disabledContentColor = PomodoroMuted
                )
            ) {
                Text(text = "应用")
            }
        }
    }
}

@Composable
private fun TaskCard(
    task: TaskItem,
    isActive: Boolean,
    isBusy: Boolean,
    onStart: () -> Unit,
    onComplete: () -> Unit
) {
    val background = if (task.completed) Color(0xFFE8E1D5) else Color(0xFFFDF8F0)
    val statusText = if (task.completed) "已完成" else "待完成"
    val statusColor = if (task.completed) PomodoroMuted else PomodoroSeal

    Surface(
        color = background,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, PomodoroLine),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = task.title,
                        color = PomodoroInk,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "${task.durationMinutes} 分钟",
                        color = PomodoroMuted,
                        fontSize = 12.sp
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(statusColor, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = statusText, color = statusColor, fontSize = 12.sp)
                }
            }
            if (isActive) {
                Text(
                    text = "当前进行中",
                    color = PomodoroSeal,
                    fontSize = 12.sp
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = onStart,
                    enabled = !task.completed && !isBusy,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PomodoroSeal,
                        contentColor = Color.White,
                        disabledContainerColor = PomodoroLine,
                        disabledContentColor = PomodoroMuted
                    )
                ) {
                    Text(text = "倒计时")
                }
                TextButton(onClick = onComplete, enabled = !task.completed) {
                    Text(text = "完成", color = PomodoroInk)
                }
            }
        }
    }
}

@Composable
private fun PomodoroFocusScreen(
    state: PomodoroState,
    onExit: () -> Unit
) {
    val context = LocalContext.current
    val gradient = remember {
        Brush.verticalGradient(
            colors = listOf(Color(0xFF0F1F1A), Color(0xFF1F3B31))
        )
    }
    val displayMode = if (
        state.timerStatus == TimerStatus.RUNNING ||
        state.timerStatus == TimerStatus.PAUSED
    ) {
        state.activeMode
    } else {
        state.selectedMode
    }
    val timeText = if (displayMode == TimerMode.COUNT_UP) {
        formatTimer(state.elapsedMillis)
    } else {
        formatTimer(state.remainingMillis)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "深度思考",
            color = PomodoroPaper,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = timeText,
            color = PomodoroPaper,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = state.activeTaskTitle.ifBlank { "未绑定任务" },
            color = PomodoroMutedLight,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = {
                    when (state.timerStatus) {
                        TimerStatus.IDLE -> startNewSession(context, state)
                        TimerStatus.RUNNING -> pauseSession(context, state)
                        TimerStatus.PAUSED -> resumeSession(context, state)
                        TimerStatus.FINISHED -> startNewSession(context, state)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = PomodoroSeal,
                    contentColor = Color.White
                )
            ) {
                val label = when (state.timerStatus) {
                    TimerStatus.IDLE -> "开始"
                    TimerStatus.RUNNING -> "暂停"
                    TimerStatus.PAUSED -> "继续"
                    TimerStatus.FINISHED -> "重新开始"
                }
                Text(text = label)
            }
            Button(
                onClick = { finishSession(state, completeTask = false) },
                enabled = state.timerStatus == TimerStatus.RUNNING || state.timerStatus == TimerStatus.PAUSED,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PomodoroPaper,
                    contentColor = PomodoroInk,
                    disabledContainerColor = PomodoroLine,
                    disabledContentColor = PomodoroMuted
                )
            ) {
                Text(text = "结束")
            }
        }
        if (state.activeTaskId != -1) {
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { finishSession(state, completeTask = true) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = PomodoroInk,
                    contentColor = PomodoroPaper
                )
            ) {
                Text(text = "完成任务")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        TextButton(onClick = onExit) {
            Text(text = "退出", color = PomodoroPaper)
        }
    }
}

@Composable
private fun PomodoroSettingsScreen(
    state: PomodoroState,
    onBack: () -> Unit
) {
    var workInput by remember { mutableStateOf(state.workMinutes.toString()) }
    var shortInput by remember { mutableStateOf(state.shortBreakMinutes.toString()) }
    var longInput by remember { mutableStateOf(state.longBreakMinutes.toString()) }
    val notificationStatus = rememberPomodoroNotificationStatus(POMODORO_CHANNEL_ALERT)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PomodoroGreenDeep)
            .padding(20.dp)
    ) {
        TextButton(onClick = onBack) {
            Text(text = "返回", color = PomodoroPaper)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "番茄设置",
            color = PomodoroPaper,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.height(16.dp))
        Surface(
            color = PomodoroPaper,
            shape = RoundedCornerShape(24.dp),
            border = BorderStroke(1.dp, PomodoroLine),
            tonalElevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = workInput,
                    onValueChange = { workInput = it },
                    label = { Text(text = "工作时长（分钟）") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = shortInput,
                    onValueChange = { shortInput = it },
                    label = { Text(text = "短休时长（分钟）") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = longInput,
                    onValueChange = { longInput = it },
                    label = { Text(text = "长休时长（分钟）") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(
                        onClick = {
                            val work = workInput.toIntOrNull()?.coerceIn(1, 180) ?: state.workMinutes
                            val shortBreak = shortInput.toIntOrNull()?.coerceIn(1, 60) ?: state.shortBreakMinutes
                            val longBreak = longInput.toIntOrNull()?.coerceIn(1, 120) ?: state.longBreakMinutes
                            state.workMinutes = work
                            state.shortBreakMinutes = shortBreak
                            state.longBreakMinutes = longBreak
                            if (state.timerStatus == TimerStatus.IDLE || state.timerStatus == TimerStatus.FINISHED) {
                                syncIdleTimer(state)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PomodoroSeal,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "保存")
                    }
                    Button(
                        onClick = {
                            state.workMinutes = 25
                            state.shortBreakMinutes = 5
                            state.longBreakMinutes = 15
                            workInput = "25"
                            shortInput = "5"
                            longInput = "15"
                            if (state.timerStatus == TimerStatus.IDLE || state.timerStatus == TimerStatus.FINISHED) {
                                syncIdleTimer(state)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PomodoroInk,
                            contentColor = PomodoroPaper
                        )
                    ) {
                        Text(text = "恢复默认")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Surface(
            color = PomodoroPaper,
            shape = RoundedCornerShape(24.dp),
            border = BorderStroke(1.dp, PomodoroLine),
            tonalElevation = 4.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "完成提醒",
                    color = PomodoroInk,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                PomodoroSwitchRow(
                    title = "震动提醒",
                    subtitle = "倒计时结束时触发震动",
                    checked = state.enableFinishVibrate,
                    onCheckedChange = { state.enableFinishVibrate = it }
                )
                PomodoroSwitchRow(
                    title = "顶部弹窗",
                    subtitle = "在其他应用上方弹出提醒",
                    checked = state.enableFinishNotification,
                    onCheckedChange = { state.enableFinishNotification = it }
                )
                PomodoroNotificationHints(
                    enabled = state.enableFinishNotification,
                    status = notificationStatus,
                    modifier = Modifier.fillMaxWidth()
                )
                Surface(
                    color = PomodoroPaper,
                    shape = RoundedCornerShape(18.dp),
                    border = BorderStroke(1.dp, PomodoroLine),
                    tonalElevation = 0.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "提醒",
                            color = PomodoroSeal,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "番茄完成",
                                color = PomodoroInk,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "休息一下，保持节奏",
                                color = PomodoroMuted,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PomodoroSwitchRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = PomodoroInk,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                color = PomodoroMuted,
                fontSize = 12.sp
            )
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = PomodoroSeal,
                uncheckedThumbColor = PomodoroPaper,
                uncheckedTrackColor = PomodoroLine
            )
        )
    }
}

private data class PomodoroNotificationStatus(
    val notificationsAllowed: Boolean,
    val headsUpEnabled: Boolean,
    val requestPermission: () -> Unit,
    val openSettings: () -> Unit
)

@Composable
private fun rememberPomodoroNotificationStatus(channelId: String): PomodoroNotificationStatus {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var notificationsAllowed by remember { mutableStateOf(canPostNotifications(context)) }
    var headsUpEnabled by remember(channelId) {
        mutableStateOf(canPomodoroHeadsUp(context, channelId))
    }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        notificationsAllowed = granted && canPostNotifications(context)
        headsUpEnabled = canPomodoroHeadsUp(context, channelId)
    }

    DisposableEffect(lifecycleOwner, context, channelId) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                notificationsAllowed = canPostNotifications(context)
                headsUpEnabled = canPomodoroHeadsUp(context, channelId)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    val openSettings = { openPomodoroNotificationSettings(context) }
    val requestPermission = {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            openSettings()
        }
    }
    return PomodoroNotificationStatus(
        notificationsAllowed = notificationsAllowed,
        headsUpEnabled = headsUpEnabled,
        requestPermission = requestPermission,
        openSettings = openSettings
    )
}

@Composable
private fun PomodoroNotificationHints(
    enabled: Boolean,
    status: PomodoroNotificationStatus,
    modifier: Modifier = Modifier
) {
    if (!enabled) return
    val showNotification = !status.notificationsAllowed
    val showHeadsUp = status.notificationsAllowed && !status.headsUpEnabled
    if (!showNotification && !showHeadsUp) return

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (showNotification) {
            PomodoroNotificationHintRow(
                title = "通知未开启",
                subtitle = "需要开启系统通知才能弹窗提醒",
                actionLabel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    "去开启"
                } else {
                    "去设置"
                },
                onAction = status.requestPermission
            )
        }
        if (showHeadsUp) {
            PomodoroNotificationHintRow(
                title = "弹窗未开启",
                subtitle = "请在系统通知设置中开启弹窗提醒",
                actionLabel = "去设置",
                onAction = status.openSettings
            )
        }
    }
}

@Composable
private fun PomodoroNotificationHintRow(
    title: String,
    subtitle: String,
    actionLabel: String,
    onAction: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(PomodoroPaper)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = PomodoroInk,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = subtitle,
                color = PomodoroMuted,
                fontSize = 12.sp
            )
        }
        Button(
            onClick = onAction,
            colors = ButtonDefaults.buttonColors(
                containerColor = PomodoroSeal,
                contentColor = Color.White
            )
        ) {
            Text(text = actionLabel)
        }
    }
}

@Composable
private fun PomodoroStatsScreen(
    state: PomodoroState,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PomodoroGreenDeep)
            .padding(20.dp)
    ) {
        TextButton(onClick = onBack) {
            Text(text = "返回", color = PomodoroPaper)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "专注统计",
            color = PomodoroPaper,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCard(label = "今日专注", value = formatDuration(state.totalFocusMillisToday))
            StatCard(label = "本周专注", value = formatDuration(state.totalFocusMillisWeek))
            StatCard(label = "完成番茄", value = "${state.completedPomodoros} 次")
            StatCard(label = "完成任务", value = "${state.completedTasks} 项")
        }
    }
}

@Composable
private fun StatCard(label: String, value: String) {
    Surface(
        color = PomodoroPaper,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, PomodoroLine),
        tonalElevation = 3.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(text = label, color = PomodoroMuted, fontSize = 13.sp)
            Text(
                text = value,
                color = PomodoroInk,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun MarqueeToolScreen(onBack: () -> Unit) {
    var marqueeText by rememberSaveable { mutableStateOf("今晚 8:00 专注时间开始") }
    var speed by rememberSaveable { mutableFloatStateOf(72f) }
    var backgroundIndex by rememberSaveable { mutableIntStateOf(0) }
    var textColorIndex by rememberSaveable { mutableIntStateOf(1) }
    var showFullScreen by rememberSaveable { mutableStateOf(false) }
    val backgroundBrushes = remember {
        listOf(
            Brush.linearGradient(
                colors = listOf(Color(0xFFFCE38A), Color(0xFFF38181)),
                start = Offset(0f, 0f),
                end = Offset(600f, 600f)
            ),
            Brush.linearGradient(
                colors = listOf(Color(0xFF42E695), Color(0xFF3BB2B8)),
                start = Offset(0f, 0f),
                end = Offset(600f, 600f)
            ),
            Brush.linearGradient(
                colors = listOf(Color(0xFFF02FC2), Color(0xFF6094EA)),
                start = Offset(0f, 0f),
                end = Offset(600f, 600f)
            ),
            Brush.linearGradient(
                colors = listOf(Color(0xFF232526), Color(0xFF414345)),
                start = Offset(0f, 0f),
                end = Offset(600f, 600f)
            )
        )
    }
    val textColors = remember {
        listOf(
            Color(0xFF212121),
            Color(0xFFF9FBE7),
            Color(0xFFF3E5F5),
            Color(0xFFE3F2FD),
            Color(0xFFFFF9C4),
            Color(0xFFFFEBEE)
        )
    }
    val scrollState = rememberScrollState()
    val textColor = textColors[textColorIndex]
    val backgroundBrush = backgroundBrushes[backgroundIndex]
    val fontSize = 30.sp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(MarqueeNight, MarqueeDeep)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            MarqueeTopBar(onBack = onBack)
            Spacer(modifier = Modifier.height(18.dp))
            MarqueePreviewCard(
                text = marqueeText.ifBlank { "请输入滚动内容" },
                speed = speed,
                fontSize = fontSize,
                textColor = textColor,
                backgroundBrush = backgroundBrush,
                direction = MarqueeDirection.LEFT,
                onDoubleTap = { showFullScreen = true }
            )
            Spacer(modifier = Modifier.height(16.dp))
            MarqueeSectionCard(
                title = "字幕内容"
            ) {
                OutlinedTextField(
                    value = marqueeText,
                    onValueChange = { marqueeText = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = "例如：请保持专注，距离休息还有 10 分钟") },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MarqueeNeon,
                        unfocusedBorderColor = MarqueeLine,
                        focusedTextColor = MarqueeText,
                        unfocusedTextColor = MarqueeText,
                        cursorColor = MarqueeNeon,
                        focusedLabelColor = MarqueeNeon,
                        unfocusedLabelColor = MarqueeMuted,
                        focusedContainerColor = MarqueePanelAlt,
                        unfocusedContainerColor = MarqueePanelAlt
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            MarqueeSectionCard(
                title = "滚动设置"
            ) {
                MarqueeOptionHeader(
                    title = "速度",
                    value = "${speed.roundToInt()} px/s"
                )
                Slider(
                    value = speed,
                    onValueChange = { speed = it },
                valueRange = 30f..500f,
                    colors = SliderDefaults.colors(
                        thumbColor = MarqueeNeon,
                        activeTrackColor = MarqueeNeon,
                        inactiveTrackColor = MarqueeLine
                    )
                )
            MarqueeOptionHeader(title = "背景颜色")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                backgroundBrushes.forEachIndexed { index, brush ->
                    MarqueeGradientChip(
                        brush = brush,
                        selected = backgroundIndex == index,
                        onSelect = { backgroundIndex = index },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
                MarqueeOptionHeader(title = "字体颜色")
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    textColors.forEachIndexed { index, color ->
                        MarqueeTextColorChip(
                            color = color,
                            selected = textColorIndex == index,
                            onSelect = { textColorIndex = index }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
        if (showFullScreen) {
            MarqueeFullScreen(
                text = marqueeText.ifBlank { "请输入滚动内容" },
                speed = speed,
                textColor = textColor,
                backgroundBrush = backgroundBrush,
                onExit = { showFullScreen = false }
            )
        }
    }
}

@Composable
private fun MarqueeTopBar(onBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        TextButton(onClick = onBack) {
            Text(text = "返回", color = MarqueeText)
        }
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "滚动字幕",
                color = MarqueeText,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        }
    }
}

@Composable
private fun MarqueePreviewCard(
    text: String,
    speed: Float,
    fontSize: TextUnit,
    textColor: Color,
    backgroundBrush: Brush,
    direction: MarqueeDirection,
    onDoubleTap: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "实时预览",
                color = MarqueeText,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "双击全屏",
                color = MarqueeMuted,
                fontSize = 12.sp
            )
        }
        Surface(
            color = Color.Transparent,
            shape = RoundedCornerShape(22.dp),
            border = BorderStroke(1.dp, MarqueeLine),
            tonalElevation = 6.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clipToBounds()
                    .background(backgroundBrush)
                    .pointerInput(Unit) {
                        detectTapGestures(onDoubleTap = { onDoubleTap() })
                    }
            ) {
                MarqueeTicker(
                    text = text,
                    speed = speed,
                    fontSize = fontSize,
                    textColor = textColor,
                    direction = direction,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun MarqueeFullScreen(
    text: String,
    speed: Float,
    textColor: Color,
    backgroundBrush: Brush,
    onExit: () -> Unit
) {
    val activity = LocalContext.current as? Activity
    DisposableEffect(Unit) {
        val previousOrientation = activity?.requestedOrientation
            ?: ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        onDispose {
            activity?.requestedOrientation = previousOrientation
        }
    }

    BackHandler(onBack = onExit)

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush)
            .pointerInput(Unit) {
                detectTapGestures(onDoubleTap = { onExit() })
            }
    ) {
        val density = LocalDensity.current
        val textMeasurer = rememberTextMeasurer()
        val targetHeightPx = with(density) { maxHeight.toPx() * 0.9f }
        val autoFontSize = remember(
            text,
            textColor,
            maxHeight,
            density.density,
            density.fontScale
        ) {
            val minPx = with(density) { 18.sp.toPx() }
            var low = minPx
            var high = targetHeightPx.coerceAtLeast(minPx)
            repeat(10) {
                val mid = (low + high) / 2f
                val measuredHeight = textMeasurer.measure(
                    text = AnnotatedString(text),
                    style = TextStyle(
                        color = textColor,
                        fontSize = with(density) { mid.toSp() },
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        letterSpacing = 1.5.sp
                    ),
                    maxLines = 1,
                    softWrap = false,
                    overflow = TextOverflow.Clip
                ).size.height.toFloat()
                if (measuredHeight <= targetHeightPx) {
                    low = mid
                } else {
                    high = mid
                }
            }
            with(density) { low.toSp() }
        }
        MarqueeTicker(
            text = text,
            speed = speed,
            fontSize = autoFontSize,
            textColor = textColor,
            direction = MarqueeDirection.LEFT,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        )
    }
}

@Composable
private fun MarqueeSectionCard(
    title: String,
    subtitle: String? = null,
    content: @Composable () -> Unit
) {
    Surface(
        color = MarqueePanel,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, MarqueeLine),
        tonalElevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = title,
                color = MarqueeText,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    color = MarqueeMuted,
                    fontSize = 12.sp
                )
            }
            content()
        }
    }
}

@Composable
private fun MarqueeOptionHeader(title: String, value: String? = null) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, color = MarqueeText, fontSize = 13.sp)
        if (value != null) {
            Text(text = value, color = MarqueeMuted, fontSize = 12.sp)
        }
    }
}

@Composable
private fun MarqueeGradientChip(
    brush: Brush,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    val chipShape = RoundedCornerShape(10.dp)
    val borderColor = if (selected) MarqueeText else MarqueeLine
    val borderWidth = if (selected) 2.dp else 1.dp
    Box(
        modifier = modifier
            .height(34.dp)
            .clip(chipShape)
            .background(brush)
            .border(borderWidth, borderColor, chipShape)
            .clickable(onClick = onSelect)
    )
}

@Composable
private fun MarqueeTextColorChip(
    color: Color,
    selected: Boolean,
    onSelect: () -> Unit
) {
    val borderColor = if (selected) MarqueeText else MarqueeLine
    val borderWidth = if (selected) 2.dp else 1.dp
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(color)
            .border(borderWidth, borderColor, CircleShape)
            .clickable(onClick = onSelect)
    )
}

@Composable
private fun MarqueeTicker(
    text: String,
    speed: Float,
    fontSize: TextUnit,
    textColor: Color,
    direction: MarqueeDirection,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val textMeasurer = rememberTextMeasurer()
    val glowRadius = with(density) { 18.dp.toPx() }
    val textStyle = remember(textColor, fontSize, glowRadius) {
        TextStyle(
            color = textColor,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 1.5.sp
        )
    }
    val measured = remember(text, textStyle) {
        textMeasurer.measure(
            text = AnnotatedString(text),
            style = textStyle
        )
    }
    BoxWithConstraints(modifier = modifier.clipToBounds()) {
        val containerWidthPx = with(density) { maxWidth.toPx() }
        val spacing = 50.dp
        val spacingPx = with(density) { spacing.toPx() }
        val textWidthPx = measured.size.width.toFloat().coerceAtLeast(1f)
        val cyclePx = (textWidthPx + spacingPx).coerceAtLeast(1f)
        val speedPxPerSecond = speed.coerceIn(30f, 500f)
        val durationMillis = ((cyclePx / speedPxPerSecond) * 1000f)
            .roundToInt()
            .coerceAtLeast(80)
        val transition = rememberInfiniteTransition(label = "marqueeTransition")
        val offset by transition.animateFloat(
            initialValue = 0f,
            targetValue = cyclePx,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = durationMillis, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ),
            label = "marqueeOffset"
        )
        val repeatCount = maxOf(3, (containerWidthPx / cyclePx).toInt() + 3)
        val startX = if (direction == MarqueeDirection.LEFT) {
            -offset
        } else {
            offset - cyclePx
        }

        Row(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth(unbounded = true)
                .offset { IntOffset(startX.roundToInt(), 0) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(repeatCount) { index ->
                Text(
                    text = text,
                    style = textStyle,
                    maxLines = 1,
                    softWrap = false
                )
                if (index < repeatCount - 1) {
                    Spacer(modifier = Modifier.width(spacing))
                }
            }
        }
    }
}

@Composable
private fun CChatScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    var config by remember { mutableStateOf(loadCChatConfig(context)) }
    var selectedModel by rememberSaveable {
        mutableStateOf(loadCChatSelectedModel(context, config.models))
    }
    var showConfig by rememberSaveable { mutableStateOf(false) }
    var input by rememberSaveable { mutableStateOf("") }
    val messages = remember { mutableStateListOf<ChatMessage>() }
    var nextId by rememberSaveable { mutableIntStateOf(0) }
    var isThinking by rememberSaveable { mutableStateOf(false) }
    var streamingMessageId by rememberSaveable { mutableIntStateOf(-1) }
    val scope = rememberCoroutineScope()
    val background = remember {
        Brush.verticalGradient(listOf(CChatBgTop, CChatBgBottom))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(WindowInsets.navigationBars.asPaddingValues())
    ) {
        CChatTopBar(title = "CChat")
        CChatModelStrip(
            models = config.models,
            selectedModel = selectedModel,
            onModelSelected = { model ->
                selectedModel = model
                saveCChatSelectedModel(context, model)
            }
        )
        if (showConfig) {
            CChatConfigCard(
                config = config,
                selectedModel = selectedModel,
                maskedEnvKey = maskEnvKey(config.envKey)
            )
        }
        Box(modifier = Modifier.weight(1f)) {
            if (messages.isEmpty()) {
                CChatEmptyState(
                    suggestions = listOf(
                        "帮我写一个社交媒体标题",
                        "总结这段文字的重点",
                        "生成本周待办清单",
                        "把这段话翻译成英文"
                    ),
                    onSuggestionTap = { input = it }
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(messages, key = { it.id }) { message ->
                        ChatMessageBubble(
                            message = message,
                            isStreaming = message.id == streamingMessageId
                        )
                    }
                    if (isThinking && messages.lastOrNull()?.role == ChatRole.USER) {
                        item {
                            CChatThinkingBubble()
                        }
                    }
                }
            }
        }
        CChatComposer(
            input = input,
            onInputChange = { input = it },
            onSend = {
                val trimmed = input.trim()
                if (trimmed.isNotBlank() && !isThinking) {
                    input = ""
                    val userId = nextId++
                    messages.add(
                        ChatMessage(
                            id = userId,
                            role = ChatRole.USER,
                            content = trimmed
                        )
                    )
                    val history = messages.toList()
                    val assistantId = nextId++
                    streamingMessageId = assistantId
                    messages.add(
                        ChatMessage(
                            id = assistantId,
                            role = ChatRole.ASSISTANT,
                            content = ""
                        )
                    )
                    val assistantIndex = messages.lastIndex
                    isThinking = true
                    scope.launch {
                        suspend fun attemptSend(): Result<String> {
                            return sendCChatRequest(
                                config = config,
                                model = selectedModel,
                                messages = history
                            ) { chunk ->
                                val current = messages.getOrNull(assistantIndex)
                                    ?: return@sendCChatRequest
                                messages[assistantIndex] = current.copy(content = current.content + chunk)
                            }
                        }

                        var result = attemptSend()
                        if (result.isFailure) {
                            val detail = result.exceptionOrNull()?.message.orEmpty()
                            val current = messages.getOrNull(assistantIndex)
                            val canRetry = current?.content?.isBlank() == true &&
                                isUsageNotIncludedError(detail)
                            if (canRetry) {
                                delay(1000L)
                                result = attemptSend()
                            }
                        }

                        if (result.isFailure) {
                            val detail = result.exceptionOrNull()?.message ?: "未知错误"
                            val current = messages.getOrNull(assistantIndex)
                            if (current != null && current.content.isBlank()) {
                                messages[assistantIndex] = current.copy(content = "请求失败：$detail")
                            }
                        }
                        isThinking = false
                        streamingMessageId = -1
                    }
                }
            },
            sendEnabled = input.trim().isNotEmpty() && !isThinking
        )
    }
}

@Composable
private fun DoushabaoScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val repository = remember { DsbRepository.getInstance(context) }
    var config by remember { mutableStateOf(loadCChatConfig(context)) }
    var selectedModel by rememberSaveable {
        mutableStateOf(loadCChatSelectedModel(context, config.models))
    }
    var input by rememberSaveable { mutableStateOf("") }
    val messages = remember { mutableStateListOf<ChatMessage>() }
    var nextId by rememberSaveable { mutableIntStateOf(0) }
    var isThinking by rememberSaveable { mutableStateOf(false) }
    var streamingMessageId by rememberSaveable { mutableIntStateOf(-1) }
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        val stored = withContext(Dispatchers.IO) {
            repository.getActiveMessages()
        }
        messages.clear()
        stored.forEach { entity ->
            messages.add(
                ChatMessage(
                    id = entity.id.toInt(),
                    role = roleFromStorage(entity.role),
                    content = entity.content
                )
            )
        }
        nextId = (stored.maxOfOrNull { it.id }?.toInt() ?: 0) + 1
    }
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.lastIndex)
        }
    }

    DsbBackground(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.navigationBars.asPaddingValues())
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            DsbTopBar(title = "豆沙包", subtitle = "温暖养成 · 记忆陪伴")
            DsbModelStrip(
                models = config.models,
                selectedModel = selectedModel,
                onModelSelected = { model ->
                    selectedModel = model
                    saveCChatSelectedModel(context, model)
                }
            )
            Box(modifier = Modifier.weight(1f)) {
                if (messages.isEmpty()) {
                    DsbEmptyState(
                        suggestions = listOf(
                            "记住我喜欢的口味",
                            "帮我整理今天的计划",
                            "推荐一个温暖小故事",
                            "我想培养一个好习惯"
                        ),
                        onSuggestionTap = { input = it }
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        state = listState,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(messages, key = { it.id }) { message ->
                            ChatMessageBubble(
                                message = message,
                                isStreaming = message.id == streamingMessageId,
                                bubblePalette = DsbBubblePalette,
                                markdownPalette = DsbMarkdownPalette,
                                fontFamily = DsbBodyFont
                            )
                        }
                        if (isThinking && messages.lastOrNull()?.role == ChatRole.USER) {
                            item {
                                DsbThinkingBubble()
                            }
                        }
                    }
                }
            }
            DsbComposer(
                input = input,
                onInputChange = { input = it },
                onSend = {
                    val trimmed = input.trim()
                    if (trimmed.isNotBlank() && !isThinking) {
                        input = ""
                        val userId = nextId++
                        messages.add(
                            ChatMessage(
                                id = userId,
                                role = ChatRole.USER,
                                content = trimmed
                            )
                        )
                        val history = messages.toList()
                        val assistantId = nextId++
                        streamingMessageId = assistantId
                        messages.add(
                            ChatMessage(
                                id = assistantId,
                                role = ChatRole.ASSISTANT,
                                content = ""
                            )
                        )
                        val assistantIndex = messages.lastIndex
                        isThinking = true
                        scope.launch {
                            withContext(Dispatchers.IO) {
                                repository.insertMessage(roleToStorage(ChatRole.USER), trimmed)
                            }
                            val summaryJson = withContext(Dispatchers.IO) {
                                repository.getLatestSummaryJson() ?: DSB_DEFAULT_SUMMARY_JSON
                            }
                            val requestMessages = buildDsbRequestMessages(summaryJson, history)

                            suspend fun attemptSend(): Result<String> {
                                return sendDsbRequest(
                                    config = config,
                                    model = selectedModel,
                                    messages = requestMessages,
                                    stream = true
                                ) { chunk ->
                                    val current = messages.getOrNull(assistantIndex)
                                        ?: return@sendDsbRequest
                                    messages[assistantIndex] = current.copy(content = current.content + chunk)
                                }
                            }

                            var result = attemptSend()
                            if (result.isFailure) {
                                val detail = result.exceptionOrNull()?.message.orEmpty()
                                val current = messages.getOrNull(assistantIndex)
                                val canRetry = current?.content?.isBlank() == true &&
                                    isUsageNotIncludedError(detail)
                                if (canRetry) {
                                    delay(1000L)
                                    result = attemptSend()
                                }
                            }

                            if (result.isFailure) {
                                val detail = result.exceptionOrNull()?.message ?: "未知错误"
                                val current = messages.getOrNull(assistantIndex)
                                if (current != null && current.content.isBlank()) {
                                    messages[assistantIndex] = current.copy(content = "请求失败：$detail")
                                }
                            }

                            val finalContent = messages.getOrNull(assistantIndex)?.content.orEmpty()
                            if (finalContent.isNotBlank()) {
                                withContext(Dispatchers.IO) {
                                    repository.insertMessage(roleToStorage(ChatRole.ASSISTANT), finalContent)
                                }
                            }
                            isThinking = false
                            streamingMessageId = -1
                            scope.launch {
                                maybeCompressDsbHistory(repository, config)
                            }
                        }
                    }
                },
                sendEnabled = input.trim().isNotEmpty() && !isThinking
            )
        }
    }
}

@Composable
private fun DsbBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val gradient = remember {
        Brush.verticalGradient(listOf(DsbBgTop, DsbBgBottom))
    }
    Box(modifier = modifier.background(gradient)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val minDim = min(size.width, size.height)
            drawCircle(
                color = DsbAccentSoft.copy(alpha = 0.35f),
                radius = minDim * 0.46f,
                center = Offset(size.width * 0.88f, size.height * 0.08f)
            )
            drawCircle(
                color = DsbCard.copy(alpha = 0.55f),
                radius = minDim * 0.36f,
                center = Offset(size.width * 0.12f, size.height * 0.16f)
            )
            drawCircle(
                color = DsbAccent.copy(alpha = 0.10f),
                radius = minDim * 0.6f,
                center = Offset(size.width * 0.32f, size.height * 0.9f)
            )
        }
        content()
    }
}

@Composable
private fun DsbTopBar(title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = DsbText,
            fontSize = 26.sp,
            fontFamily = DsbDisplayFont,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = subtitle,
            color = DsbMuted,
            fontSize = 12.sp,
            fontFamily = DsbBodyFont,
            letterSpacing = 0.4.sp
        )
    }
}

@Composable
private fun DsbModelStrip(
    models: List<String>,
    selectedModel: String,
    onModelSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        models.forEach { model ->
            FilterChip(
                selected = model == selectedModel,
                onClick = { onModelSelected(model) },
                label = {
                    Text(
                        text = model,
                        fontFamily = DsbBodyFont,
                        fontSize = 12.sp
                    )
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = DsbAccent,
                    selectedLabelColor = Color.White,
                    containerColor = DsbCard,
                    labelColor = DsbMuted
                )
            )
        }
    }
}

@Composable
private fun DsbEmptyState(
    suggestions: List<String>,
    onSuggestionTap: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "豆沙包",
            color = DsbText,
            fontSize = 30.sp,
            fontFamily = DsbDisplayFont,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "慢慢认识你的温柔小伙伴",
            color = DsbMuted,
            fontSize = 14.sp,
            fontFamily = DsbBodyFont
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            suggestions.chunked(2).forEach { rowItems ->
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    rowItems.forEach { suggestion ->
                        DsbSuggestionChip(
                            text = suggestion,
                            onClick = { onSuggestionTap(suggestion) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun DsbSuggestionChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = DsbAccentSoft,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            color = DsbAccent,
            fontSize = 12.sp,
            fontFamily = DsbBodyFont,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)
        )
    }
}

@Composable
private fun DsbThinkingBubble() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Surface(
            color = DsbCard,
            shape = RoundedCornerShape(18.dp),
            border = BorderStroke(1.dp, DsbLine),
            modifier = Modifier.fillMaxWidth(0.72f)
        ) {
            Text(
                text = "豆沙包在想…",
                color = DsbMuted,
                fontSize = 13.sp,
                fontFamily = DsbBodyFont,
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
            )
        }
    }
}

@Composable
private fun DsbComposer(
    input: String,
    onInputChange: (String) -> Unit,
    onSend: () -> Unit,
    sendEnabled: Boolean
) {
    Surface(
        color = DsbSurface,
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(1.dp, DsbLine),
        tonalElevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedTextField(
                value = input,
                onValueChange = onInputChange,
                placeholder = {
                    Text(
                        text = "和豆沙包聊聊吧…",
                        fontFamily = DsbBodyFont
                    )
                },
                minLines = 1,
                maxLines = 6,
                textStyle = TextStyle(
                    color = DsbText,
                    fontFamily = DsbBodyFont,
                    fontSize = 15.sp
                ),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = DsbAccent,
                    unfocusedBorderColor = DsbLine,
                    cursorColor = DsbAccent,
                    focusedContainerColor = DsbSurface,
                    unfocusedContainerColor = DsbSurface,
                    disabledContainerColor = DsbSurface
                ),
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = onSend,
                enabled = sendEnabled,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DsbAccent,
                    contentColor = Color.White,
                    disabledContainerColor = DsbLine,
                    disabledContentColor = DsbMuted
                ),
                modifier = Modifier.height(56.dp)
            ) {
                Text(
                    text = "发送",
                    fontFamily = DsbBodyFont
                )
            }
        }
    }
}

@Composable
private fun CChatTopBar(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        Text(
            text = title,
            color = CChatText,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun CChatModelStrip(
    models: List<String>,
    selectedModel: String,
    onModelSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            models.forEach { model ->
                FilterChip(
                    selected = model == selectedModel,
                    onClick = { onModelSelected(model) },
                    label = { Text(text = model) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = CChatAccent,
                        selectedLabelColor = Color.White,
                        containerColor = CChatCard,
                        labelColor = CChatMuted
                    )
                )
            }
        }
    }
}

@Composable
private fun CChatConfigCard(
    config: CChatConfig,
    selectedModel: String,
    maskedEnvKey: String
) {
    Surface(
        color = CChatSurface,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, CChatLine),
        tonalElevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "请求参数",
                color = CChatText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            CChatConfigRow(label = "model", value = selectedModel)
            CChatConfigRow(label = "model_reasoning_effort", value = config.reasoningEffort)
            CChatConfigRow(
                label = "disable_response_storage",
                value = config.disableResponseStorage.toString()
            )
            CChatConfigRow(
                label = "requires_openai_auth",
                value = config.requiresOpenAiAuth.toString()
            )
            CChatConfigRow(label = "preferred_auth_method", value = config.preferredAuthMethod)
            CChatConfigRow(label = "base_url", value = config.baseUrl)
            CChatConfigRow(label = "wire_api", value = config.wireApi)
            CChatConfigRow(label = "env_key", value = maskedEnvKey)
            Text(
                text = "env_key 已隐藏，建议从安全存储读取。",
                color = CChatMuted,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
private fun CChatConfigRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = label,
            color = CChatMuted,
            fontSize = 12.sp,
            modifier = Modifier.width(160.dp)
        )
        Text(
            text = value,
            color = CChatText,
            fontSize = 12.sp,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun CChatEmptyState(
    suggestions: List<String>,
    onSuggestionTap: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "CChat",
            color = CChatText,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "简洁流畅的 AI 问答体验",
            color = CChatMuted,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            suggestions.chunked(2).forEach { rowItems ->
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    rowItems.forEach { suggestion ->
                        CChatSuggestionChip(
                            text = suggestion,
                            onClick = { onSuggestionTap(suggestion) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun CChatSuggestionChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = CChatAccentSoft,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            color = CChatAccent,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)
        )
    }
}

private sealed class MarkdownBlock {
    data class Paragraph(val text: String) : MarkdownBlock()
    data class Heading(val text: String, val level: Int) : MarkdownBlock()
    data class Code(val text: String, val language: String?) : MarkdownBlock()
    data class ListBlock(val items: List<String>, val ordered: Boolean) : MarkdownBlock()
    data class QuoteBlock(val text: String) : MarkdownBlock()
    data class TableBlock(val headers: List<String>, val rows: List<List<String>>) : MarkdownBlock()
}

@Composable
private fun MarkdownMessage(
    text: String,
    isStreaming: Boolean,
    textColor: Color,
    palette: MarkdownPalette,
    fontFamily: FontFamily?
) {
    val blocks = remember(text) { parseMarkdownBlocks(text) }
    Box {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            if (blocks.isEmpty()) {
                Text(text = "", color = textColor)
            } else {
                blocks.forEach { block ->
                    when (block) {
                        is MarkdownBlock.Paragraph -> MarkdownParagraph(        
                            text = block.text,
                            textColor = textColor,
                            palette = palette,
                            fontFamily = fontFamily
                        )
                        is MarkdownBlock.Heading -> MarkdownHeading(
                            text = block.text,
                            level = block.level,
                            textColor = textColor,
                            palette = palette,
                            fontFamily = fontFamily
                        )
                        is MarkdownBlock.Code -> MarkdownCodeBlock(
                            text = block.text,
                            language = block.language,
                            palette = palette
                        )
                        is MarkdownBlock.ListBlock -> MarkdownListBlock(        
                            items = block.items,
                            ordered = block.ordered,
                            textColor = textColor,
                            palette = palette,
                            fontFamily = fontFamily
                        )
                        is MarkdownBlock.QuoteBlock -> MarkdownQuoteBlock(      
                            text = block.text,
                            textColor = textColor,
                            palette = palette,
                            fontFamily = fontFamily
                        )
                        is MarkdownBlock.TableBlock -> MarkdownTableBlock(      
                            headers = block.headers,
                            rows = block.rows,
                            textColor = textColor,
                            palette = palette,
                            fontFamily = fontFamily
                        )
                    }
                }
            }
        }
        if (isStreaming) {
            StreamingCursor(
                color = palette.cursor,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 8.dp, bottom = 6.dp)
            )
        }
    }
}

@Composable
private fun MarkdownHeading(
    text: String,
    level: Int,
    textColor: Color,
    palette: MarkdownPalette,
    fontFamily: FontFamily?
) {
    val size = when (level.coerceIn(1, 3)) {
        1 -> 18.sp
        2 -> 16.sp
        else -> 15.sp
    }
    MarkdownInlineText(
        text = text,
        textColor = textColor,
        fontSize = size,
        lineHeight = 22.sp,
        palette = palette,
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
private fun MarkdownParagraph(
    text: String,
    textColor: Color,
    palette: MarkdownPalette,
    fontFamily: FontFamily?
) {
    MarkdownInlineText(
        text = text,
        textColor = textColor,
        fontSize = 15.sp,
        lineHeight = 21.sp,
        palette = palette,
        fontFamily = fontFamily
    )
}

@Composable
private fun MarkdownInlineText(
    text: String,
    textColor: Color,
    fontSize: TextUnit,
    lineHeight: TextUnit,
    palette: MarkdownPalette,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily?
) {
    val annotated = remember(text, textColor, palette.accent, palette.codeBg, palette.codeText) {
        buildMarkdownAnnotatedString(text, textColor, palette.accent, palette.codeBg, palette.codeText)
    }
    val uriHandler = LocalUriHandler.current
    ClickableText(
        text = annotated,
        style = TextStyle(
            color = textColor,
            fontSize = fontSize,
            lineHeight = lineHeight,
            fontWeight = fontWeight,
            fontFamily = fontFamily
        ),
        onClick = { offset ->
            annotated.getStringAnnotations("URL", offset, offset)
                .firstOrNull()
                ?.let { uriHandler.openUri(it.item) }
        }
    )
}

@Composable
private fun MarkdownListBlock(
    items: List<String>,
    ordered: Boolean,
    textColor: Color,
    palette: MarkdownPalette,
    fontFamily: FontFamily?
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        items.forEachIndexed { index, item ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = if (ordered) "${index + 1}." else "•",
                    color = palette.muted,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(top = 2.dp),
                    fontFamily = fontFamily
                )
                MarkdownInlineText(
                    text = item,
                    textColor = textColor,
                    fontSize = 15.sp,
                    lineHeight = 21.sp,
                    palette = palette,
                    fontFamily = fontFamily
                )
            }
        }
    }
}

@Composable
private fun MarkdownQuoteBlock(
    text: String,
    textColor: Color,
    palette: MarkdownPalette,
    fontFamily: FontFamily?
) {
    Surface(
        color = palette.quoteBg,
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, palette.quoteBorder)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(2.dp))
                    .background(palette.accent)
            )
            MarkdownInlineText(
                text = text,
                textColor = textColor,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                palette = palette,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun MarkdownTableBlock(
    headers: List<String>,
    rows: List<List<String>>,
    textColor: Color,
    palette: MarkdownPalette,
    fontFamily: FontFamily?
) {
    val scrollState = rememberScrollState()
    Surface(
        color = palette.tableSurface,
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, palette.tableBorder)
    ) {
        Column(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            MarkdownTableRow(
                cells = headers,
                isHeader = true,
                textColor = textColor,
                palette = palette,
                fontFamily = fontFamily
            )
            rows.forEach { row ->
                MarkdownTableRow(
                    cells = row,
                    isHeader = false,
                    textColor = textColor,
                    palette = palette,
                    fontFamily = fontFamily
                )
            }
        }
    }
}

@Composable
private fun MarkdownTableRow(
    cells: List<String>,
    isHeader: Boolean,
    textColor: Color,
    palette: MarkdownPalette,
    fontFamily: FontFamily?
) {
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        cells.forEach { cell ->
            Surface(
                color = if (isHeader) palette.tableHeaderBg else Color.Transparent,
                border = BorderStroke(1.dp, palette.tableBorder),
                shape = RoundedCornerShape(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .widthIn(min = 120.dp)
                        .padding(horizontal = 10.dp, vertical = 8.dp)
                ) {
                    MarkdownInlineText(
                        text = cell,
                        textColor = textColor,
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        palette = palette,
                        fontFamily = fontFamily,
                        fontWeight = if (isHeader) FontWeight.SemiBold else null
                    )
                }
            }
        }
    }
}

@Composable
private fun MarkdownCodeBlock(text: String, language: String?, palette: MarkdownPalette) {
    val scrollState = rememberScrollState()
    Surface(
        color = palette.codeBg,
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, palette.codeBorder)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            if (!language.isNullOrBlank()) {
                Text(
                    text = language,
                    color = palette.muted,
                    fontSize = 11.sp
                )
            }
            Text(
                text = text,
                color = palette.codeText,
                fontSize = 13.sp,
                fontFamily = FontFamily.Monospace,
                lineHeight = 18.sp,
                modifier = Modifier.horizontalScroll(scrollState)
            )
        }
    }
}

@Composable
private fun StreamingCursor(color: Color, modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "cursorBlink")
    val alpha by transition.animateFloat(
        initialValue = 0.15f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 620, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "cursorAlpha"
    )
    Box(
        modifier = modifier
            .size(width = 6.dp, height = 18.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(color)
            .alpha(alpha)
    )
}

private data class CodeFenceStart(val language: String?, val firstLine: String?)

private val knownCodeFenceLanguages = listOf(
    "cpp", "c++", "c", "python", "java", "kotlin", "swift", "go", "rust",
    "javascript", "js", "typescript", "ts", "bash", "shell", "json", "yaml",
    "xml", "html", "css", "sql", "csharp", "cs"
)

private fun parseCodeFenceStart(line: String): CodeFenceStart? {
    if (!line.startsWith("```")) return null
    val raw = line.removePrefix("```")
    if (raw.isBlank()) return CodeFenceStart(null, null)
    val trimmed = raw.trimStart()
    if (trimmed.isBlank()) return CodeFenceStart(null, null)
    val firstToken = trimmed.takeWhile { !it.isWhitespace() }
    val remainder = trimmed.drop(firstToken.length).trimStart()
    val normalized = firstToken.lowercase(Locale.US)
    val match = knownCodeFenceLanguages.firstOrNull { normalized.startsWith(it) }
    if (match != null && firstToken.length > match.length) {
        val fused = firstToken.substring(match.length).trimStart()
        val combined = buildString {
            if (fused.isNotBlank()) append(fused)
            if (remainder.isNotBlank()) {
                if (isNotEmpty()) append(' ')
                append(remainder)
            }
        }
        return CodeFenceStart(match, combined.ifBlank { null })
    }
    if (remainder.isNotBlank()) {
        return CodeFenceStart(firstToken, remainder)
    }
    if (raw.any { it.isWhitespace() }) {
        return CodeFenceStart(firstToken, null)
    }
    return CodeFenceStart(firstToken, null)
}

private fun isTableSeparator(line: String): Boolean {
    val trimmed = line.trim()
    if (!trimmed.contains('-')) return false
    if (!trimmed.contains('|')) return false
    return trimmed.all { it == '|' || it == '-' || it == ':' || it.isWhitespace() }
}

private fun splitTableRow(line: String): List<String> {
    val trimmed = line.trim().trim('|')
    if (trimmed.isBlank()) return listOf("")
    return trimmed.split('|').map { it.trim() }
}

private fun parseListItem(line: String): Pair<Boolean, String>? {
    val trimmed = line.trim()
    if (trimmed.startsWith("- ") || trimmed.startsWith("* ") || trimmed.startsWith("+ ")) {
        return false to trimmed.drop(2).trim()
    }
    val match = Regex("^(\\d+)\\.\\s+(.+)").find(trimmed) ?: return null
    return true to match.groupValues[2].trim()
}

private fun parseMarkdownBlocks(text: String): List<MarkdownBlock> {
    if (text.isBlank()) return emptyList()
    val lines = text.replace("\r\n", "\n").split('\n')
    val blocks = mutableListOf<MarkdownBlock>()
    val paragraph = StringBuilder()
    val code = StringBuilder()
    var inCode = false
    var codeLang: String? = null

    fun flushParagraph() {
        val value = paragraph.toString().trimEnd()
        if (value.isNotBlank()) {
            blocks.add(MarkdownBlock.Paragraph(value))
        }
        paragraph.setLength(0)
    }

    fun flushCode() {
        val value = code.toString().trimEnd()
        blocks.add(MarkdownBlock.Code(value, codeLang))
        code.setLength(0)
        codeLang = null
    }

    var index = 0
    while (index < lines.size) {
        val line = lines[index]
        val trimmed = line.trim()

        if (inCode) {
            if (trimmed == "```") {
                flushCode()
                inCode = false
                index += 1
                continue
            }
            code.append(line).append('\n')
            index += 1
            continue
        }

        val fence = parseCodeFenceStart(trimmed)
        if (fence != null) {
            flushParagraph()
            inCode = true
            codeLang = fence.language
            fence.firstLine?.let { code.append(it).append('\n') }
            index += 1
            continue
        }

        if (trimmed.isBlank()) {
            flushParagraph()
            index += 1
            continue
        }

        if (trimmed.startsWith(">")) {
            flushParagraph()
            val quoteLines = mutableListOf<String>()
            while (index < lines.size && lines[index].trim().startsWith(">")) {
                quoteLines.add(lines[index].trim().removePrefix(">").trimStart())
                index += 1
            }
            blocks.add(MarkdownBlock.QuoteBlock(quoteLines.joinToString("\n").trim()))
            continue
        }

        if (line.contains("|") && index + 1 < lines.size && isTableSeparator(lines[index + 1])) {
            flushParagraph()
            val headers = splitTableRow(line)
            val rows = mutableListOf<List<String>>()
            index += 2
            while (index < lines.size) {
                val rowLine = lines[index]
                if (rowLine.trim().isBlank() || !rowLine.contains("|")) break
                rows.add(splitTableRow(rowLine))
                index += 1
            }
            blocks.add(MarkdownBlock.TableBlock(headers, rows))
            continue
        }

        val listItem = parseListItem(trimmed)
        if (listItem != null) {
            flushParagraph()
            val ordered = listItem.first
            val items = mutableListOf(listItem.second)
            index += 1
            while (index < lines.size) {
                val next = parseListItem(lines[index].trim())
                if (next == null || next.first != ordered) break
                items.add(next.second)
                index += 1
            }
            blocks.add(MarkdownBlock.ListBlock(items, ordered))
            continue
        }

        if (trimmed.startsWith("#")) {
            flushParagraph()
            val level = trimmed.takeWhile { it == '#' }.length.coerceIn(1, 3)
            val heading = trimmed.drop(level).trim()
            if (heading.isNotBlank()) {
                blocks.add(MarkdownBlock.Heading(heading, level))
            }
            index += 1
            continue
        }

        paragraph.append(line).append('\n')
        index += 1
    }

    if (inCode) {
        flushCode()
    } else {
        flushParagraph()
    }

    return blocks
}

private fun buildMarkdownAnnotatedString(
    text: String,
    textColor: Color,
    linkColor: Color,
    codeBg: Color,
    codeText: Color
): AnnotatedString {
    val builder = AnnotatedString.Builder()
    var index = 0
    while (index < text.length) {
        when {
            text.startsWith("**", index) -> {
                val end = text.indexOf("**", index + 2)
                if (end != -1) {
                    val value = text.substring(index + 2, end)
                    builder.withStyle(SpanStyle(fontWeight = FontWeight.SemiBold, color = textColor)) {
                        append(value)
                    }
                    index = end + 2
                    continue
                }
            }
            text[index] == '`' -> {
                val end = text.indexOf('`', index + 1)
                if (end != -1) {
                    val value = text.substring(index + 1, end)
                    builder.withStyle(
                        SpanStyle(
                            fontFamily = FontFamily.Monospace,
                            background = codeBg,
                            color = codeText
                        )
                    ) {
                        append(value)
                    }
                    index = end + 1
                    continue
                }
            }
            text[index] == '*' || text[index] == '_' -> {
                val marker = text[index]
                val end = text.indexOf(marker, index + 1)
                if (end != -1) {
                    val value = text.substring(index + 1, end)
                    builder.withStyle(SpanStyle(fontStyle = FontStyle.Italic, color = textColor)) {
                        append(value)
                    }
                    index = end + 1
                    continue
                }
            }
            text[index] == '[' -> {
                val endLabel = text.indexOf("](", index + 1)
                if (endLabel != -1) {
                    val endUrl = text.indexOf(')', endLabel + 2)
                    if (endUrl != -1) {
                        val label = text.substring(index + 1, endLabel)
                        val url = text.substring(endLabel + 2, endUrl)
                        builder.pushStringAnnotation(tag = "URL", annotation = url)
                        builder.withStyle(
                            SpanStyle(
                                color = linkColor,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append(label)
                        }
                        builder.pop()
                        index = endUrl + 1
                        continue
                    }
                }
            }
        }
        builder.append(text[index])
        index += 1
    }
    return builder.toAnnotatedString()
}

private data class MarkdownPalette(
    val accent: Color,
    val muted: Color,
    val quoteBg: Color,
    val quoteBorder: Color,
    val tableSurface: Color,
    val tableHeaderBg: Color,
    val tableBorder: Color,
    val codeBg: Color,
    val codeBorder: Color,
    val codeText: Color,
    val cursor: Color
)

private data class ChatBubblePalette(
    val userBubble: Color,
    val assistantBubble: Color,
    val userText: Color,
    val assistantText: Color,
    val line: Color,
    val copyBg: Color,
    val copyBorder: Color,
    val copyText: Color,
    val accent: Color,
    val accentSoft: Color,
    val muted: Color
)

@Composable
private fun ChatMessageBubble(
    message: ChatMessage,
    isStreaming: Boolean,
    bubblePalette: ChatBubblePalette = CChatBubblePalette,
    markdownPalette: MarkdownPalette = CChatMarkdownPalette,
    fontFamily: FontFamily? = null
) {
    val isUser = message.role == ChatRole.USER
    val bubbleColor = if (isUser) bubblePalette.userBubble else bubblePalette.assistantBubble
    val textColor = if (isUser) bubblePalette.userText else bubblePalette.assistantText
    val alignment = if (isUser) Arrangement.End else Arrangement.Start
    val contentAlignment = if (isUser) Alignment.End else Alignment.Start
    val bubbleWidth = if (isUser) 0.78f else 0.86f
    val maxBubbleWidth = (LocalConfiguration.current.screenWidthDp * bubbleWidth).dp
    val bubbleModifier = if (isUser) {
        Modifier.widthIn(max = maxBubbleWidth)
    } else {
        Modifier.fillMaxWidth(bubbleWidth)
    }
    val clipboard = LocalClipboardManager.current
    var copied by remember(message.id) { mutableStateOf(false) }
    val copyBackground by animateColorAsState(
        targetValue = if (copied) bubblePalette.accentSoft else bubblePalette.copyBg,
        label = "copyBackground"
    )
    val copyBorder by animateColorAsState(
        targetValue = if (copied) bubblePalette.accent else bubblePalette.copyBorder,
        label = "copyBorder"
    )
    val copyTextColor by animateColorAsState(
        targetValue = if (copied) bubblePalette.accent else bubblePalette.copyText,
        label = "copyText"
    )

    LaunchedEffect(copied) {
        if (copied) {
            delay(900L)
            copied = false
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = contentAlignment
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = alignment
        ) {
            Surface(
                color = bubbleColor,
                shape = RoundedCornerShape(18.dp),
                border = if (isUser) null else BorderStroke(1.dp, bubblePalette.line),
                modifier = bubbleModifier
            ) {
                if (isUser) {
                    Text(
                        text = message.content,
                        color = textColor,
                        fontSize = 15.sp,
                        fontFamily = fontFamily,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                    )
                } else {
                    Box(modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)) {
                        MarkdownMessage(
                            text = message.content,
                            isStreaming = isStreaming,
                            textColor = textColor,
                            palette = markdownPalette,
                            fontFamily = fontFamily
                        )
                    }
                }
            }
        }
        if (!isUser) {
            Surface(
                color = copyBackground,
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, copyBorder),
                modifier = Modifier
                    .padding(top = 6.dp)
                    .clickable(enabled = message.content.isNotBlank()) {
                        if (message.content.isNotBlank()) {
                            clipboard.setText(AnnotatedString(message.content))
                            copied = true
                        }
                    }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(if (copied) bubblePalette.accent else bubblePalette.muted)
                    )
                    Text(
                        text = if (copied) "已复制" else "复制",
                        color = copyTextColor,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun CChatThinkingBubble() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Surface(
            color = CChatCard,
            shape = RoundedCornerShape(18.dp),
            border = BorderStroke(1.dp, CChatLine),
            modifier = Modifier.fillMaxWidth(0.72f)
        ) {
            Text(
                text = "正在思考…",
                color = CChatMuted,
                fontSize = 13.sp,
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
            )
        }
    }
}

@Composable
private fun CChatComposer(
    input: String,
    onInputChange: (String) -> Unit,
    onSend: () -> Unit,
    sendEnabled: Boolean
) {
    Surface(
        color = CChatSurface,
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(1.dp, CChatLine),
        tonalElevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedTextField(
                value = input,
                onValueChange = onInputChange,
                placeholder = { Text(text = "输入你的问题…") },
                minLines = 1,
                maxLines = 6,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = CChatAccent,
                    unfocusedBorderColor = CChatLine,
                    cursorColor = CChatAccent
                ),
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = onSend,
                enabled = sendEnabled,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CChatAccent,
                    contentColor = Color.White,
                    disabledContainerColor = CChatLine,
                    disabledContentColor = CChatMuted
                ),
                modifier = Modifier.height(56.dp)
            ) {
                Text(text = "发送")
            }
        }
    }
}

private fun maskEnvKey(value: String): String {
    if (value.length <= 12) return value
    val prefix = value.take(6)
    val suffix = value.takeLast(4)
    return "$prefix...$suffix"
}

private fun loadCChatConfig(context: Context): CChatConfig {
    val prefs = context.getSharedPreferences(CCHAT_PREFS_NAME, Context.MODE_PRIVATE)
    var shouldPersist = false

    fun readString(key: String, fallback: String): String {
        val value = prefs.getString(key, null)
        if (value == null) {
            shouldPersist = true
            return fallback
        }
        return value
    }

    fun readBoolean(key: String, fallback: Boolean): Boolean {
        if (!prefs.contains(key)) {
            shouldPersist = true
            return fallback
        }
        return prefs.getBoolean(key, fallback)
    }

    val reasoningEffort = readString(CCHAT_KEY_REASONING_EFFORT, CCHAT_DEFAULT_REASONING_EFFORT)
    val disableStorage = readBoolean(CCHAT_KEY_DISABLE_STORAGE, CCHAT_DEFAULT_DISABLE_STORAGE)
    val requiresAuth = readBoolean(CCHAT_KEY_REQUIRES_AUTH, CCHAT_DEFAULT_REQUIRES_AUTH)
    val preferredAuth = readString(CCHAT_KEY_PREFERRED_AUTH, CCHAT_DEFAULT_AUTH_METHOD)
    val baseUrl = CCHAT_DEFAULT_BASE_URL
    val wireApi = CCHAT_DEFAULT_WIRE_API
    val envKey = CCHAT_DEFAULT_ENV_KEY
    if (prefs.getString(CCHAT_KEY_BASE_URL, null) != baseUrl) {
        shouldPersist = true
    }
    if (prefs.getString(CCHAT_KEY_WIRE_API, null) != wireApi) {
        shouldPersist = true
    }
    if (prefs.getString(CCHAT_KEY_ENV_KEY, null) != envKey) {
        shouldPersist = true
    }

    if (shouldPersist) {
        prefs.edit()
            .putString(CCHAT_KEY_REASONING_EFFORT, reasoningEffort)
            .putBoolean(CCHAT_KEY_DISABLE_STORAGE, disableStorage)
            .putBoolean(CCHAT_KEY_REQUIRES_AUTH, requiresAuth)
            .putString(CCHAT_KEY_PREFERRED_AUTH, preferredAuth)
            .putString(CCHAT_KEY_BASE_URL, baseUrl)
            .putString(CCHAT_KEY_WIRE_API, wireApi)
            .putString(CCHAT_KEY_ENV_KEY, envKey)
            .apply()
    }

    return CChatConfig(
        models = CCHAT_DEFAULT_MODELS,
        reasoningEffort = reasoningEffort,
        disableResponseStorage = disableStorage,
        requiresOpenAiAuth = requiresAuth,
        preferredAuthMethod = preferredAuth,
        baseUrl = baseUrl,
        wireApi = wireApi,
        envKey = envKey
    )
}

private fun loadCChatSelectedModel(context: Context, models: List<String>): String {
    val prefs = context.getSharedPreferences(CCHAT_PREFS_NAME, Context.MODE_PRIVATE)
    val stored = prefs.getString(CCHAT_KEY_SELECTED_MODEL, null)
    val fallback = when {
        models.contains(CCHAT_DEFAULT_SELECTED_MODEL) -> CCHAT_DEFAULT_SELECTED_MODEL
        models.isNotEmpty() -> models.first()
        else -> CCHAT_DEFAULT_SELECTED_MODEL
    }
    val resolved = if (stored != null && models.contains(stored)) stored else fallback
    if (stored == null || stored != resolved) {
        prefs.edit().putString(CCHAT_KEY_SELECTED_MODEL, resolved).apply()
    }
    return resolved
}

private fun saveCChatSelectedModel(context: Context, model: String) {
    context.getSharedPreferences(CCHAT_PREFS_NAME, Context.MODE_PRIVATE)
        .edit()
        .putString(CCHAT_KEY_SELECTED_MODEL, model)
        .apply()
}

private suspend fun sendCChatRequest(
    config: CChatConfig,
    model: String,
    messages: List<ChatMessage>,
    onChunk: (String) -> Unit
): Result<String> {
    val baseUrl = config.baseUrl.trim()
    val wireApi = config.wireApi.trim()
    if (baseUrl.isBlank()) {
        return Result.failure(IllegalStateException("base_url 为空"))
    }
    if (wireApi.isBlank()) {
        return Result.failure(IllegalStateException("wire_api 为空"))
    }
    if (config.requiresOpenAiAuth && config.envKey.isBlank()) {
        return Result.failure(IllegalStateException("env_key 为空"))
    }
    if (!wireApi.equals("responses", ignoreCase = true)) {
        return Result.failure(IllegalStateException("仅支持 responses 协议"))
    }

    val endpoint = buildCChatEndpoint(baseUrl, wireApi)
    val history = if (messages.size > 16) messages.takeLast(16) else messages
    val payload = buildCChatPayload(config, model, history)

    return withContext(Dispatchers.IO) {
        try {
            val connection = (URL(endpoint).openConnection() as HttpURLConnection).apply {
                requestMethod = "POST"
                connectTimeout = 20000
                readTimeout = 30000
                setRequestProperty("Content-Type", "application/json")
                setRequestProperty("Accept", "text/event-stream")
                if (config.requiresOpenAiAuth &&
                    config.preferredAuthMethod.equals("apikey", ignoreCase = true)
                ) {
                    setRequestProperty("Authorization", "Bearer ${config.envKey}")
                }
                doOutput = true
            }

            connection.outputStream.use { output ->
                output.write(payload.toByteArray(Charsets.UTF_8))
            }

            if (connection.responseCode !in 200..299) {
                val responseBody = readConnectionBody(connection)
                val message = parseCChatError(responseBody)
                    ?: formatCChatHttpError(connection.responseCode, responseBody)
                return@withContext Result.failure(IOException(message))
            }

            parseCChatStream(connection.inputStream) { chunk ->
                withContext(Dispatchers.Main) {
                    onChunk(chunk)
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

private fun buildCChatEndpoint(baseUrl: String, wireApi: String): String {
    val normalizedBase = baseUrl.trim().trimEnd('/')
    val normalizedApi = wireApi.trim().trimStart('/')
    return "$normalizedBase/$normalizedApi"
}

private fun buildCChatPayload(
    config: CChatConfig,
    model: String,
    messages: List<ChatMessage>
): String {
    val payload = JSONObject()
    payload.put("model", model)
    payload.put("instructions", CCHAT_DEFAULT_INSTRUCTIONS)
    payload.put("input", buildCChatInput(messages))
    payload.put("store", !config.disableResponseStorage)
    payload.put("stream", true)
    val effort = config.reasoningEffort.lowercase(Locale.US)
    if (effort.isNotBlank()) {
        payload.put("reasoning", JSONObject().put("effort", effort))
    }
    return payload.toString()
}

private fun buildCChatInput(messages: List<ChatMessage>): JSONArray {
    val input = JSONArray()
    messages.forEach { message ->
        val role = if (message.role == ChatRole.USER) "user" else "assistant"
        val contentType = if (message.role == ChatRole.USER) "input_text" else "output_text"
        val content = JSONArray().apply {
            put(JSONObject().put("type", contentType).put("text", message.content))
        }
        val item = JSONObject()
        item.put("role", role)
        item.put("content", content)
        input.put(item)
    }
    return input
}

private data class DsbRequestMessage(val role: String, val content: String)

private fun roleToStorage(role: ChatRole): String {
    return if (role == ChatRole.ASSISTANT) "assistant" else "user"
}

private fun roleFromStorage(role: String): ChatRole {
    return if (role.equals("assistant", ignoreCase = true)) ChatRole.ASSISTANT else ChatRole.USER
}

private fun buildDsbMemorySystemPrompt(summaryJson: String): String {
    return """
你是“豆沙包”。下面的 MEMORY_JSON 是长期记忆背景，只在相关时使用，不要生硬引用。
如与最新对话冲突，以最新用户消息为准；不确定时先澄清。
MEMORY_JSON:
$summaryJson
""".trimIndent()
}

private fun buildDsbRequestMessages(
    summaryJson: String,
    history: List<ChatMessage>
): List<DsbRequestMessage> {
    val payload = mutableListOf<DsbRequestMessage>()
    payload.add(DsbRequestMessage(role = "system", content = buildDsbMemorySystemPrompt(summaryJson)))
    history.takeLast(DSB_RECENT_MESSAGE_KEEP).forEach { message ->
        payload.add(
            DsbRequestMessage(
                role = roleToStorage(message.role),
                content = message.content
            )
        )
    }
    return payload
}

private fun buildDsbCompressionUserPrompt(
    summaryJson: String,
    messages: List<DsbMessageEntity>
): String {
    val builder = StringBuilder()
    builder.append("existing_summary_json:\n")
    builder.append(summaryJson.trim()).append("\n\n")
    builder.append("new_messages:\n")
    messages.forEachIndexed { index, message ->
        builder.append("${index + 1}) ${message.role}: ${message.content}\n")
    }
    builder.append("请输出更新后的 summary_json（严格 JSON）。")
    return builder.toString()
}

private fun buildDsbInput(messages: List<DsbRequestMessage>): JSONArray {
    val input = JSONArray()
    messages.forEach { message ->
        val contentType = if (message.role.equals("assistant", ignoreCase = true)) {
            "output_text"
        } else {
            "input_text"
        }
        val content = JSONArray().apply {
            put(JSONObject().put("type", contentType).put("text", message.content))
        }
        val item = JSONObject()
        item.put("role", message.role)
        item.put("content", content)
        input.put(item)
    }
    return input
}

private fun buildDsbPayload(
    config: CChatConfig,
    model: String,
    messages: List<DsbRequestMessage>,
    stream: Boolean
): String {
    val payload = JSONObject()
    payload.put("model", model)
    payload.put("instructions", CCHAT_DEFAULT_INSTRUCTIONS)
    payload.put("input", buildDsbInput(messages))
    payload.put("store", !config.disableResponseStorage)
    payload.put("stream", stream)
    val effort = config.reasoningEffort.lowercase(Locale.US)
    if (effort.isNotBlank()) {
        payload.put("reasoning", JSONObject().put("effort", effort))
    }
    return payload.toString()
}

private suspend fun sendDsbRequest(
    config: CChatConfig,
    model: String,
    messages: List<DsbRequestMessage>,
    stream: Boolean,
    onChunk: (String) -> Unit
): Result<String> {
    val baseUrl = config.baseUrl.trim()
    val wireApi = config.wireApi.trim()
    if (baseUrl.isBlank()) {
        return Result.failure(IllegalStateException("base_url 为空"))
    }
    if (wireApi.isBlank()) {
        return Result.failure(IllegalStateException("wire_api 为空"))
    }
    if (config.requiresOpenAiAuth && config.envKey.isBlank()) {
        return Result.failure(IllegalStateException("env_key 为空"))
    }
    if (!wireApi.equals("responses", ignoreCase = true)) {
        return Result.failure(IllegalStateException("仅支持 responses 协议"))
    }

    val endpoint = buildCChatEndpoint(baseUrl, wireApi)
    val payload = buildDsbPayload(config, model, messages, stream)

    return withContext(Dispatchers.IO) {
        try {
            val connection = (URL(endpoint).openConnection() as HttpURLConnection).apply {
                requestMethod = "POST"
                connectTimeout = 20000
                readTimeout = 30000
                setRequestProperty("Content-Type", "application/json")
                setRequestProperty("Accept", if (stream) "text/event-stream" else "application/json")
                if (config.requiresOpenAiAuth &&
                    config.preferredAuthMethod.equals("apikey", ignoreCase = true)
                ) {
                    setRequestProperty("Authorization", "Bearer ${config.envKey}")
                }
                doOutput = true
            }

            connection.outputStream.use { output ->
                output.write(payload.toByteArray(Charsets.UTF_8))
            }

            if (connection.responseCode !in 200..299) {
                val responseBody = readConnectionBody(connection)
                val message = parseCChatError(responseBody)
                    ?: formatCChatHttpError(connection.responseCode, responseBody)
                return@withContext Result.failure(IOException(message))
            }

            if (stream) {
                parseCChatStream(connection.inputStream) { chunk ->
                    withContext(Dispatchers.Main) {
                        onChunk(chunk)
                    }
                }
            } else {
                val responseBody = readConnectionBody(connection)
                val text = parseCChatResponse(responseBody)
                if (text.isNullOrBlank()) {
                    Result.failure(IOException("响应解析失败"))
                } else {
                    Result.success(text)
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

private suspend fun maybeCompressDsbHistory(
    repository: DsbRepository,
    config: CChatConfig
): Result<Unit> {
    return DSB_COMPRESSION_MUTEX.withLock {
        val activeMessages = withContext(Dispatchers.IO) {
            repository.getActiveMessages()
        }
        if (activeMessages.size <= DSB_RECENT_MESSAGE_KEEP) {
            return@withLock Result.success(Unit)
        }
        val compressTargets = activeMessages.dropLast(DSB_RECENT_MESSAGE_KEEP)
        val tokenTotal = compressTargets.sumOf { it.tokenCount.toLong() }
        if (tokenTotal < DSB_COMPRESSION_TOKEN_THRESHOLD) {
            return@withLock Result.success(Unit)
        }
        val latestSummary = withContext(Dispatchers.IO) {
            repository.getLatestSummaryJson() ?: DSB_DEFAULT_SUMMARY_JSON
        }
        val userPrompt = buildDsbCompressionUserPrompt(latestSummary, compressTargets)
        val request = listOf(
            DsbRequestMessage(role = "system", content = DSB_COMPRESSION_SYSTEM_PROMPT),
            DsbRequestMessage(role = "user", content = userPrompt)
        )
        val result = sendDsbRequest(
            config = config,
            model = DSB_COMPRESSION_MODEL,
            messages = request,
            stream = false,
            onChunk = {}
        )
        if (result.isFailure) {
            val error = result.exceptionOrNull() ?: IOException("压缩失败")
            return@withLock Result.failure(error)
        }
        val raw = result.getOrNull().orEmpty()
        val json = extractFirstJsonObject(raw)
            ?: return@withLock Result.failure(IOException("压缩结果不是有效 JSON"))
        val parsed = try {
            JSONObject(json)
        } catch (e: Exception) {
            null
        }
        if (parsed == null) {
            return@withLock Result.failure(IOException("压缩结果不是有效 JSON"))
        }
        val lastId = compressTargets.last().id
        withContext(Dispatchers.IO) {
            repository.insertSummary(json, lastId, tokenTotal)
            repository.archiveMessagesUpTo(lastId)
        }
        Result.success(Unit)
    }
}

private fun extractFirstJsonObject(text: String): String? {
    val start = text.indexOf('{')
    if (start == -1) return null
    var depth = 0
    for (index in start until text.length) {
        when (text[index]) {
            '{' -> depth += 1
            '}' -> {
                depth -= 1
                if (depth == 0) {
                    return text.substring(start, index + 1)
                }
            }
        }
    }
    return null
}

private fun parseCChatResponse(body: String): String? {
    return try {
        val json = JSONObject(body)
        when (val outputText = json.opt("output_text")) {
            is String -> outputText
            is JSONArray -> {
                val parts = mutableListOf<String>()
                for (i in 0 until outputText.length()) {
                    val value = outputText.optString(i)
                    if (value.isNotBlank()) {
                        parts.add(value)
                    }
                }
                parts.joinToString("\n").takeIf { it.isNotBlank() }
            }
            else -> {
                extractTextFromOutput(json.optJSONArray("output"))
                    ?: extractTextFromChoices(json.optJSONArray("choices"))
            }
        }
    } catch (e: Exception) {
        null
    }
}

private suspend fun parseCChatStream(
    stream: java.io.InputStream,
    onChunk: suspend (String) -> Unit
): Result<String> {
    stream.bufferedReader(Charsets.UTF_8).use { reader ->
        val builder = StringBuilder()
        val rawBuffer = StringBuilder()
        val eventData = StringBuilder()
        var sawDataLine = false

        suspend fun applyChunk(chunk: StreamChunk?) {
            if (chunk == null) return
            val delta = if (chunk.isDelta) {
                chunk.text
            } else {
                computeStreamingDelta(chunk.text, builder.toString())
            }
            if (!delta.isNullOrBlank()) {
                onChunk(delta)
                builder.append(delta)
            }
        }

        for (line in reader.lineSequence()) {
            rawBuffer.append(line).append('\n')
            if (line.startsWith("data:")) {
                sawDataLine = true
                eventData.append(line.removePrefix("data:").trim()).append('\n')
            } else if (line.isBlank()) {
                val data = eventData.toString().trim()
                if (data.isNotBlank()) {
                    if (data == "[DONE]") break
                    val result = parseCChatStreamEvent(data, builder.isNotEmpty())
                    if (result.isFailure) {
                        return Result.failure(result.exceptionOrNull() ?: IOException("响应解析失败"))
                    }
                    applyChunk(result.getOrNull())
                }
                eventData.setLength(0)
            }
        }

        val tail = eventData.toString().trim()
        if (tail.isNotBlank() && tail != "[DONE]") {
            val result = parseCChatStreamEvent(tail, builder.isNotEmpty())
            if (result.isFailure) {
                return Result.failure(result.exceptionOrNull() ?: IOException("响应解析失败"))
            }
            applyChunk(result.getOrNull())
        }

        if (!sawDataLine) {
            val raw = rawBuffer.toString().trim()
            if (raw.isNotBlank()) {
                val fallback = parseCChatResponse(raw)
                if (!fallback.isNullOrBlank()) {
                    onChunk(fallback)
                    return Result.success(fallback)
                }
            }
        }

        val text = builder.toString()
        return if (text.isBlank()) {
            Result.failure(IOException("响应解析失败"))
        } else {
            Result.success(text)
        }
    }
}

private data class StreamChunk(val text: String, val isDelta: Boolean)

private data class ContentExtract(val text: String, val isDelta: Boolean)

private fun computeStreamingDelta(fullText: String, current: String): String? {
    if (fullText.isBlank()) return null
    if (current.isBlank()) return fullText
    if (fullText == current) return null
    if (fullText.startsWith(current)) {
        val delta = fullText.substring(current.length)
        return delta.takeIf { it.isNotBlank() }
    }
    val maxOverlap = min(current.length, fullText.length)
    for (i in maxOverlap downTo 1) {
        if (current.regionMatches(current.length - i, fullText, 0, i, ignoreCase = false)) {
            val delta = fullText.substring(i)
            return delta.takeIf { it.isNotBlank() }
        }
    }
    return fullText
}

private fun parseCChatStreamEvent(data: String, hasContent: Boolean): Result<StreamChunk?> {
    return try {
        val json = JSONObject(data)
        val errorMessage = json.optJSONObject("error")?.optString("message")
        if (!errorMessage.isNullOrBlank()) {
            return Result.failure(IOException(errorMessage))
        }

        val type = json.optString("type")
        val isDeltaType = type.contains("delta", ignoreCase = true)
        val isDoneType = type.contains("done", ignoreCase = true)
        if (json.has("delta")) {
            val delta = json.optString("delta")
            return Result.success(delta.takeIf { it.isNotBlank() }?.let { StreamChunk(it, true) })
        }
        if (json.has("text")) {
            val text = json.optString("text")
            if (isDoneType && hasContent) {
                return Result.success(null)
            }
            return Result.success(text.takeIf { it.isNotBlank() }?.let { StreamChunk(it, isDeltaType) })
        }

        val outputTextValue = json.opt("output_text")
        val outputText = extractTextFromOutputTextValue(outputTextValue)        
        if (!outputText.isNullOrBlank()) {
            if (isDoneType && hasContent) {
                return Result.success(null)
            }
            return Result.success(StreamChunk(outputText, isDeltaType))
        }

        val contentText = extractTextFromContentArray(json.optJSONArray("content"))
        if (contentText != null && contentText.text.isNotBlank()) {
            return Result.success(StreamChunk(contentText.text, contentText.isDelta || isDeltaType))
        }

        val outputTextFromOutput = extractTextFromOutput(json.optJSONArray("output"))
        if (!outputTextFromOutput.isNullOrBlank()) {
            return Result.success(StreamChunk(outputTextFromOutput, isDeltaType))
        }

        val responseObject = json.optJSONObject("response")
        if (responseObject != null) {
            val responseText = parseCChatResponse(responseObject.toString())
            if (!responseText.isNullOrBlank()) {
                return Result.success(StreamChunk(responseText, false))
            }
        }

        Result.success(null)
    } catch (e: Exception) {
        Result.failure(e)
    }
}

private fun extractTextFromOutputTextValue(value: Any?): String? {
    return when (value) {
        is String -> value.takeIf { it.isNotBlank() }
        is JSONArray -> {
            val parts = mutableListOf<String>()
            for (i in 0 until value.length()) {
                val piece = value.optString(i)
                if (piece.isNotBlank()) {
                    parts.add(piece)
                }
            }
            parts.joinToString("\n").takeIf { it.isNotBlank() }
        }
        else -> null
    }
}

private fun extractTextFromContentArray(content: JSONArray?): ContentExtract? {
    if (content == null) return null
    val parts = mutableListOf<String>()
    var sawDelta = false
    for (i in 0 until content.length()) {
        val item = content.optJSONObject(i) ?: continue
        val text = item.optString("text")
        val delta = item.optString("delta")
        when {
            delta.isNotBlank() -> {
                sawDelta = true
                parts.add(delta)
            }
            text.isNotBlank() -> parts.add(text)
        }
    }
    val joined = parts.joinToString("")
    return joined.takeIf { it.isNotBlank() }?.let { ContentExtract(it, sawDelta) }
}

private fun extractTextFromOutput(output: JSONArray?): String? {
    if (output == null) return null
    val parts = StringBuilder()
    for (i in 0 until output.length()) {
        val item = output.optJSONObject(i) ?: continue
        val content = item.optJSONArray("content") ?: continue
        for (j in 0 until content.length()) {
            val piece = content.optJSONObject(j) ?: continue
            val text = piece.optString("text")
            if (text.isNotBlank()) {
                if (parts.isNotEmpty()) parts.append('\n')
                parts.append(text)
            }
        }
    }
    return parts.toString().takeIf { it.isNotBlank() }
}

private fun extractTextFromChoices(choices: JSONArray?): String? {
    if (choices == null || choices.length() == 0) return null
    val first = choices.optJSONObject(0) ?: return null
    val message = first.optJSONObject("message")
    val content = message?.optString("content")
    if (!content.isNullOrBlank()) return content
    val text = first.optString("text")
    return text.takeIf { it.isNotBlank() }
}

private fun parseCChatError(body: String): String? {
    return try {
        val json = JSONObject(body)
        json.optJSONObject("error")?.optString("message")?.takeIf { it.isNotBlank() }
    } catch (e: Exception) {
        null
    }
}

private fun formatCChatHttpError(code: Int, body: String): String {
    val compact = body.replace(Regex("\\s+"), " ").trim()
    return if (compact.isNotBlank()) {
        val truncated = if (compact.length > 240) {
            compact.take(240) + "..."
        } else {
            compact
        }
        "HTTP $code: $truncated"
    } else {
        "HTTP $code"
    }
}

private fun isUsageNotIncludedError(message: String): Boolean {
    val lower = message.lowercase(Locale.US)
    return lower.contains("usage_not_included") ||
        lower.contains("usage not included in your plan") ||
        (lower.contains("http 429") && lower.contains("plan_type"))
}

private fun readConnectionBody(connection: HttpURLConnection): String {
    val stream = if (connection.responseCode in 200..299) {
        connection.inputStream
    } else {
        connection.errorStream
    }
    if (stream == null) return ""
    return stream.bufferedReader(Charsets.UTF_8).use { it.readText() }
}

private enum class ApiStatsPeriod(val label: String, val apiValue: String) {
    DAILY("今日", "daily"),
    MONTHLY("本月", "monthly")
}

private data class ApiStatsLimits(
    val dailyCostLimit: Double,
    val currentDailyCost: Double
)

private data class ApiModelStat(
    val model: String,
    val requests: Long,
    val inputTokens: Long,
    val outputTokens: Long,
    val cacheReadTokens: Long,
    val totalCostLabel: String
)

private data class ApiStatsSummary(
    val name: String,
    val isActive: Boolean,
    val permissions: String,
    val limits: ApiStatsLimits,
    val models: List<ApiModelStat>,
    val period: ApiStatsPeriod
)

@Composable
private fun ApiStatsScreen(onBack: () -> Unit) {
    var apiKey by remember { mutableStateOf("") }
    var period by rememberSaveable { mutableStateOf(ApiStatsPeriod.DAILY) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var summary by remember { mutableStateOf<ApiStatsSummary?>(null) }
    val scope = rememberCoroutineScope()

    val gradient = remember {
        Brush.verticalGradient(colors = listOf(CChatBgTop, CChatBgBottom))
    }
    val canQuery = apiKey.trim().isNotBlank() && !isLoading

    fun submitQuery(selectedPeriod: ApiStatsPeriod) {
        val trimmedKey = apiKey.trim()
        if (trimmedKey.isBlank() || isLoading) return
        isLoading = true
        errorMessage = null
        scope.launch {
            val result = fetchApiStatsSummary(trimmedKey, selectedPeriod)
            isLoading = false
            result.onSuccess { data ->
                summary = data
            }.onFailure { error ->
                errorMessage = error.message?.takeIf { it.isNotBlank() } ?: "查询失败"
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ApiStatsTopBar(onBack = onBack)
        }
        item {
            ApiStatsSectionCard(
                title = "API Key 查询",
                subtitle = "仅用于查询，不会保存"
            ) {
                OutlinedTextField(
                    value = apiKey,
                    onValueChange = { apiKey = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = "请输入 API Key (cr_...)") },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CChatAccent,
                        unfocusedBorderColor = CChatLine,
                        focusedTextColor = CChatText,
                        unfocusedTextColor = CChatText,
                        cursorColor = CChatAccent,
                        focusedContainerColor = CChatSurface,
                        unfocusedContainerColor = CChatSurface
                    )
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ApiStatsPeriod.values().forEach { option ->
                        FilterChip(
                            selected = period == option,
                            onClick = {
                                period = option
                                if (summary != null) {
                                    submitQuery(option)
                                }
                            },
                            label = { Text(option.label) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = CChatAccent,
                                selectedLabelColor = Color.White,
                                containerColor = CChatCopyBg,
                                labelColor = CChatText
                            )
                        )
                    }
                }
                Button(
                    onClick = { submitQuery(period) },
                    enabled = canQuery,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CChatAccent,
                        contentColor = Color.White,
                        disabledContainerColor = CChatLine,
                        disabledContentColor = CChatMuted
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = if (isLoading) "查询中..." else "查询统计")
                }
            }
        }

        if (isLoading) {
            item {
                ApiStatsSectionCard(title = "正在获取数据") {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth(),
                        color = CChatAccent
                    )
                    Text(text = "正在拉取统计信息，请稍候", color = CChatMuted, fontSize = 12.sp)
                }
            }
        }

        if (errorMessage != null) {
            item {
                ApiStatsSectionCard(title = "查询失败") {
                    Text(
                        text = errorMessage ?: "",
                        color = Color(0xFFB00020),
                        fontSize = 13.sp
                    )
                }
            }
        }

        summary?.let { data ->
            item {
                ApiStatsSectionCard(title = "API Key 信息") {
                    ApiStatsMetricRow("名称", data.name)
                    ApiStatsMetricRow("状态", if (data.isActive) "活跃" else "停用")
                    ApiStatsMetricRow("权限", data.permissions.ifBlank { "未知" })
                }
            }
            item {
                ApiStatsSectionCard(title = "每日费用限制") {
                    val limitText = if (data.limits.dailyCostLimit > 0) {
                        formatApiCost(data.limits.dailyCostLimit)
                    } else {
                        "未设置"
                    }
                    val currentText = formatApiCost(data.limits.currentDailyCost)
                    ApiStatsMetricRow("当前/上限", "$currentText / $limitText")
                }
            }
            item {
                ApiStatsSectionCard(
                    title = "模型使用统计",
                    subtitle = "时间范围：${data.period.label}"
                ) {
                    if (data.models.isEmpty()) {
                        Text(text = "暂无数据", color = CChatMuted, fontSize = 12.sp)
                    }
                }
            }
            items(data.models) { model ->
                ApiStatsModelCard(model = model)
            }
        }
    }
}

@Composable
private fun ApiStatsTopBar(onBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        TextButton(onClick = onBack) {
            Text(text = "返回", color = CChatText)
        }
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "API 统计",
                color = CChatText,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "查询费用限制与模型用量",
                color = CChatMuted,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun ApiStatsSectionCard(
    title: String,
    subtitle: String? = null,
    content: @Composable () -> Unit
) {
    Surface(
        color = CChatSurface,
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(1.dp, CChatLine),
        tonalElevation = 3.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            content = {
                Text(
                    text = title,
                    color = CChatText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        color = CChatMuted,
                        fontSize = 12.sp
                    )
                }
                content()
            }
        )
    }
}

@Composable
private fun ApiStatsMetricRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = CChatMuted, fontSize = 12.sp)
        Text(text = value, color = CChatText, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun ApiStatsModelCard(model: ApiModelStat) {
    Surface(
        color = CChatSurface,
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(1.dp, CChatLine),
        tonalElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = model.model,
                    color = CChatText,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = model.totalCostLabel,
                    color = CChatAccent,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            ApiStatsMetricRow("请求次数", formatCompactNumber(model.requests))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ApiStatsMetricItem(
                    label = "输入 Token",
                    value = formatCompactNumber(model.inputTokens),
                    modifier = Modifier.weight(1f)
                )
                ApiStatsMetricItem(
                    label = "输出 Token",
                    value = formatCompactNumber(model.outputTokens),
                    modifier = Modifier.weight(1f)
                )
                ApiStatsMetricItem(
                    label = "缓存读取",
                    value = formatCompactNumber(model.cacheReadTokens),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun ApiStatsMetricItem(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = label, color = CChatMuted, fontSize = 11.sp)
        Text(text = value, color = CChatText, fontSize = 13.sp, fontWeight = FontWeight.Medium)
    }
}

private const val API_STATS_KEY_ID_ENDPOINT = "http://card.fishtrip.top/apiStats/api/get-key-id"
private const val API_STATS_USER_STATS_ENDPOINT = "http://card.fishtrip.top/apiStats/api/user-stats"
private const val API_STATS_MODEL_STATS_ENDPOINT = "http://card.fishtrip.top/apiStats/api/user-model-stats"

private suspend fun fetchApiStatsSummary(
    apiKey: String,
    period: ApiStatsPeriod
): Result<ApiStatsSummary> {
    return withContext(Dispatchers.IO) {
        try {
            val apiId = requestApiStatsId(apiKey)
            val statsBody = postApiStatsJson(
                API_STATS_USER_STATS_ENDPOINT,
                JSONObject().put("apiId", apiId)
            )
            val statsJson = JSONObject(statsBody)
            if (!statsJson.optBoolean("success")) {
                val message = extractApiStatsMessage(statsJson) ?: "统计查询失败"
                return@withContext Result.failure(IOException(message))
            }
            val data = statsJson.optJSONObject("data")
                ?: return@withContext Result.failure(IOException("统计数据缺失"))
            val limits = parseApiStatsLimits(data.optJSONObject("limits"))
            val name = data.optString("name").ifBlank { "未命名" }
            val isActive = data.optBoolean("isActive", false)
            val permissions = data.optString("permissions")

            val modelBody = postApiStatsJson(
                API_STATS_MODEL_STATS_ENDPOINT,
                JSONObject()
                    .put("apiId", apiId)
                    .put("period", period.apiValue)
            )
            val modelJson = JSONObject(modelBody)
            if (!modelJson.optBoolean("success")) {
                val message = extractApiStatsMessage(modelJson) ?: "模型统计查询失败"
                return@withContext Result.failure(IOException(message))
            }
            val models = parseApiStatsModels(modelJson.optJSONArray("data"))

            Result.success(
                ApiStatsSummary(
                    name = name,
                    isActive = isActive,
                    permissions = permissions,
                    limits = limits,
                    models = models,
                    period = period
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

private fun requestApiStatsId(apiKey: String): String {
    val body = postApiStatsJson(
        API_STATS_KEY_ID_ENDPOINT,
        JSONObject().put("apiKey", apiKey)
    )
    val json = JSONObject(body)
    if (!json.optBoolean("success")) {
        val message = extractApiStatsMessage(json) ?: "API Key 无效"
        throw IOException(message)
    }
    val id = json.optJSONObject("data")?.optString("id").orEmpty()
    if (id.isBlank()) {
        throw IOException("API Key 无效")
    }
    return id
}

private fun postApiStatsJson(endpoint: String, payload: JSONObject): String {
    val connection = (URL(endpoint).openConnection() as HttpURLConnection).apply {
        requestMethod = "POST"
        connectTimeout = 15000
        readTimeout = 20000
        setRequestProperty("Content-Type", "application/json")
        setRequestProperty("Accept", "application/json")
        doOutput = true
    }
    connection.outputStream.use { output ->
        output.write(payload.toString().toByteArray(Charsets.UTF_8))
    }
    if (connection.responseCode !in 200..299) {
        val responseBody = readConnectionBody(connection)
        throw IOException(formatCChatHttpError(connection.responseCode, responseBody))
    }
    return readConnectionBody(connection)
}

private fun extractApiStatsMessage(json: JSONObject): String? {
    return json.optString("message").takeIf { it.isNotBlank() }
        ?: json.optJSONObject("error")?.optString("message")?.takeIf { it.isNotBlank() }
}

private fun parseApiStatsLimits(limitsJson: JSONObject?): ApiStatsLimits {
    if (limitsJson == null) {
        return ApiStatsLimits(dailyCostLimit = 0.0, currentDailyCost = 0.0)
    }
    return ApiStatsLimits(
        dailyCostLimit = limitsJson.optDouble("dailyCostLimit", 0.0),
        currentDailyCost = limitsJson.optDouble("currentDailyCost", 0.0)
    )
}

private fun parseApiStatsModels(models: JSONArray?): List<ApiModelStat> {
    if (models == null) return emptyList()
    val items = mutableListOf<ApiModelStat>()
    for (i in 0 until models.length()) {
        val item = models.optJSONObject(i) ?: continue
        val model = item.optString("model").ifBlank { "未知模型" }
        val requests = item.optLong("requests", 0L)
        val inputTokens = item.optLong("inputTokens", 0L)
        val outputTokens = item.optLong("outputTokens", 0L)
        val cacheReadTokens = item.optLong("cacheReadTokens", 0L)
        val formattedTotal = item.optJSONObject("formatted")
            ?.optString("total")
            ?.takeIf { it.isNotBlank() }
        val totalCost = item.optJSONObject("costs")?.optDouble("total", Double.NaN) ?: Double.NaN
        val totalCostLabel = formattedTotal
            ?: if (!totalCost.isNaN()) formatApiCost(totalCost) else "--"
        items.add(
            ApiModelStat(
                model = model,
                requests = requests,
                inputTokens = inputTokens,
                outputTokens = outputTokens,
                cacheReadTokens = cacheReadTokens,
                totalCostLabel = totalCostLabel
            )
        )
    }
    return items
}

private fun formatApiCost(value: Double): String {
    if (!value.isFinite()) return "$0.00"
    return String.format(Locale.US, "$%.2f", value)
}

private fun formatCompactNumber(value: Long): String {
    val absValue = kotlin.math.abs(value.toDouble())
    val (divisor, suffix) = when {
        absValue >= 1_000_000_000 -> 1_000_000_000.0 to "B"
        absValue >= 1_000_000 -> 1_000_000.0 to "M"
        absValue >= 1_000 -> 1_000.0 to "K"
        else -> return value.toString()
    }
    val formatted = String.format(Locale.US, "%.1f", value / divisor)
    val cleaned = formatted.removeSuffix(".0")
    return "$cleaned$suffix"
}

@Composable
private fun ImageCompressionScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    var meta by remember { mutableStateOf<ImageMeta?>(null) }
    var compressionRatio by rememberSaveable { mutableFloatStateOf(0.2f) }
    var targetSizeInput by rememberSaveable { mutableStateOf("") }
    var qualityMax by rememberSaveable { mutableIntStateOf(90) }
    var compressionMode by rememberSaveable { mutableStateOf(CompressionMode.RATIO) }
    var outputFormat by rememberSaveable { mutableStateOf(ImageFormat.JPEG) }
    var keepExif by rememberSaveable { mutableStateOf(true) }
    var compressionStatus by rememberSaveable { mutableStateOf(CompressionStatus.IDLE) }
    var compressedBytes by rememberSaveable { mutableLongStateOf(0L) }
    var compressedData by remember { mutableStateOf<ByteArray?>(null) }
    var previewBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var compressedPreviewBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var compressionOutcome by remember { mutableStateOf<CompressionOutcome?>(null) }
    var isProcessing by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()
    val legacyPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted: Boolean ->
        if (granted) {
            val currentMeta = meta ?: return@rememberLauncherForActivityResult
            val data = compressedData ?: return@rememberLauncherForActivityResult
            if (isProcessing) return@rememberLauncherForActivityResult
            isProcessing = true
            message = "保存中..."
            errorMessage = null
            scope.launch {
                val saved = withContext<Boolean>(Dispatchers.IO) {
                    saveCompressedImage(
                        context = context,
                        sourceUri = currentMeta.uri,
                        displayName = currentMeta.displayName,
                        data = data,
                        format = outputFormat,
                        keepExif = keepExif
                    )
                }
                isProcessing = false
                if (saved) {
                    message = "已保存到相册"
                } else {
                    errorMessage = "保存失败，请重试"
                }
            }
        } else {
            errorMessage = "需要存储权限才能保存到相册"
        }
    }

    val picker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            meta = readImageMeta(context, uri)
            compressedBytes = 0L
            compressedData = null
            previewBitmap = null
            compressedPreviewBitmap = null
            compressionOutcome = null
            message = null
            errorMessage = null
            compressionStatus = CompressionStatus.READY
        }
    }

    LaunchedEffect(compressionRatio, outputFormat, targetSizeInput, qualityMax, compressionMode) {
        if (!isProcessing && meta != null) {
            compressedBytes = 0L
            compressedData = null
            compressedPreviewBitmap = null
            compressionOutcome = null
            compressionStatus = CompressionStatus.READY
            message = null
            errorMessage = null
        }
    }

    LaunchedEffect(meta?.uri) {
        previewBitmap = null
        val currentUri = meta?.uri ?: return@LaunchedEffect
        previewBitmap = withContext<Bitmap?>(Dispatchers.IO) {
            loadPreviewBitmap(context, currentUri, 800)
        }
    }

    LaunchedEffect(compressedData) {
        compressedPreviewBitmap = null
        val data = compressedData ?: return@LaunchedEffect
        compressedPreviewBitmap = withContext<Bitmap?>(Dispatchers.IO) {
            decodePreviewFromBytes(data, 800)
        }
    }

    val targetBytesOverride = if (compressionMode == CompressionMode.TARGET_SIZE) {
        parseTargetSizeMb(targetSizeInput)
    } else {
        null
    }
    val baseSize = meta?.sizeBytes ?: 0L
    val estimatedBytes = when {
        compressionMode == CompressionMode.TARGET_SIZE -> targetBytesOverride ?: 0L
        baseSize > 0L -> (baseSize * compressionRatio).toLong()
        else -> 0L
    }
    val ratioPercent = (compressionRatio * 100f).roundToInt()
    val canCompress = meta != null && !isProcessing &&
        (compressionMode == CompressionMode.RATIO || targetBytesOverride != null)
    val validationMessage = if (
        compressionMode == CompressionMode.TARGET_SIZE &&
        targetBytesOverride == null &&
        meta != null
    ) {
        "请输入有效的目标大小（MB）"
    } else {
        null
    }
    val conversionHint = when {
        meta == null -> null
        baseSize <= 0L -> "无法读取原图大小，无法换算"
        compressionMode == CompressionMode.TARGET_SIZE && targetBytesOverride != null -> {
            val percent = targetBytesOverride.toDouble() / baseSize.toDouble() * 100.0
            "按原图估算：约等于 ${String.format("%.1f", percent)}%"
        }
        compressionMode == CompressionMode.TARGET_SIZE -> "按原图估算：请输入目标大小"
        else -> "按原图估算：目标大小≈${formatBytes(estimatedBytes)}"
    }
    val gradient = remember {
        Brush.verticalGradient(colors = listOf(BookBgDeep, BookBgLight))
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ImageToolTopBar(onBack = onBack)
        }
        item {
            ImagePickerCard(
                meta = meta,
                previewBitmap = previewBitmap,
                onPick = { picker.launch("image/*") },
                onClear = {
                    meta = null
                    compressionStatus = CompressionStatus.IDLE
                    compressedBytes = 0L
                    compressedData = null
                    previewBitmap = null
                    compressedPreviewBitmap = null
                    compressionOutcome = null
                    message = null
                    errorMessage = null
                }
            )
        }
        item {
            CompressionSettingsCard(
                mode = compressionMode,
                onModeChange = { compressionMode = it },
                ratioPercent = ratioPercent,
                ratio = compressionRatio,
                onRatioChange = { compressionRatio = it },
                targetSizeInput = targetSizeInput,
                onTargetSizeChange = { targetSizeInput = it },
                qualityMax = qualityMax,
                onQualityMaxChange = { qualityMax = it },
                outputFormat = outputFormat,
                onFormatChange = { outputFormat = it },
                keepExif = keepExif,
                onKeepExifChange = { keepExif = it },
                estimatedBytes = estimatedBytes,
                conversionHint = conversionHint,
                enabled = !isProcessing
            )
        }
        item {
            CompressionResultCard(
                status = compressionStatus,
                targetBytes = targetBytesOverride,
                estimatedBytes = estimatedBytes,
                compressedBytes = compressedBytes,
                enabled = canCompress,
                isProcessing = isProcessing,
                message = message,
                errorMessage = errorMessage,
                outputFormat = outputFormat,
                compressionMode = compressionMode,
                qualityMax = qualityMax,
                validationMessage = validationMessage,
                previewBitmap = compressedPreviewBitmap,
                targetMet = compressionOutcome?.metTarget,
                reachedQualityLimit = compressionOutcome?.reachedQualityLimit ?: false,
                reachedScaleLimit = compressionOutcome?.reachedScaleLimit ?: false,
                onCompress = {
                    val currentMeta = meta ?: return@CompressionResultCard
                    if (isProcessing) return@CompressionResultCard
                    isProcessing = true
                    message = "压缩中..."
                    errorMessage = null
                    compressedData = null
                    compressedPreviewBitmap = null
                    compressionOutcome = null
                    scope.launch {
                        val result = withContext<CompressionOutcome?>(Dispatchers.IO) {
                            compressImage(
                                context = context,
                                meta = currentMeta,
                                ratio = compressionRatio,
                                targetBytesOverride = targetBytesOverride,
                                format = outputFormat,
                                qualityMax = qualityMax
                            )
                        }
                        isProcessing = false
                        if (result != null) {
                            compressedData = result.data
                            compressedBytes = result.data.size.toLong()
                            compressionOutcome = result
                            compressionStatus = CompressionStatus.COMPRESSED
                            message = "压缩完成"
                        } else {
                            errorMessage = "压缩失败，请重试"
                        }
                    }
                },
                onSave = {
                    val currentMeta = meta ?: return@CompressionResultCard
                    val data = compressedData ?: return@CompressionResultCard
                    if (isProcessing) return@CompressionResultCard
                    val needPermission = Build.VERSION.SDK_INT < Build.VERSION_CODES.Q &&
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ) != PackageManager.PERMISSION_GRANTED
                    if (needPermission) {
                        legacyPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        return@CompressionResultCard
                    }
                    isProcessing = true
                    message = "保存中..."
                    errorMessage = null
                    scope.launch {
                        val saved = withContext<Boolean>(Dispatchers.IO) {
                            saveCompressedImage(
                                context = context,
                                sourceUri = currentMeta.uri,
                                displayName = currentMeta.displayName,
                                data = data,
                                format = outputFormat,
                                keepExif = keepExif
                            )
                        }
                        isProcessing = false
                        if (saved) {
                            message = "已保存到相册"
                        } else {
                            errorMessage = "保存失败，请重试"
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun ImageToolTopBar(onBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        TextButton(onClick = onBack) {
            Text(text = "返回", color = BookInk)
        }
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "图片压缩",
                color = BookInk,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "默认 JPEG · 保留 EXIF",
                color = BookMuted,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun ImagePickerCard(
    meta: ImageMeta?,
    previewBitmap: Bitmap?,
    onPick: () -> Unit,
    onClear: () -> Unit
) {
    Surface(
        color = BookCard,
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(1.dp, BookLine),
        tonalElevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "图片来源",
                        color = BookInk,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                    Text(text = "从图库选择原图", color = BookMuted, fontSize = 12.sp)
                }
                Button(
                    onClick = onPick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BookSeal,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "从图库挑选")
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .border(1.dp, BookLine, RoundedCornerShape(16.dp))
                    .background(BookMist),
                contentAlignment = Alignment.Center
            ) {
                if (previewBitmap != null) {
                    Image(
                        bitmap = previewBitmap.asImageBitmap(),
                        contentDescription = "原图预览",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text(
                        text = meta?.displayName ?: "尚未选择图片",
                        color = BookMuted,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MetaItem(
                    label = "原图大小",
                    value = formatBytes(meta?.sizeBytes ?: 0L),
                    modifier = Modifier.weight(1f)
                )
                MetaItem(
                    label = "分辨率",
                    value = meta?.let { "${it.width}×${it.height}" } ?: "--",
                    modifier = Modifier.weight(1f)
                )
                MetaItem(
                    label = "格式",
                    value = meta?.let { mimeLabel(it.mimeType) } ?: "--",
                    modifier = Modifier.weight(1f)
                )
            }
            TextButton(onClick = onClear, enabled = meta != null) {
                Text(text = "清空", color = BookInk)
            }
        }
    }
}

@Composable
private fun MetaItem(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = label, color = BookMuted, fontSize = 12.sp)
        Text(text = value, color = BookInk, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun CompressionSettingsCard(
    mode: CompressionMode,
    onModeChange: (CompressionMode) -> Unit,
    ratioPercent: Int,
    ratio: Float,
    onRatioChange: (Float) -> Unit,
    targetSizeInput: String,
    onTargetSizeChange: (String) -> Unit,
    qualityMax: Int,
    onQualityMaxChange: (Int) -> Unit,
    outputFormat: ImageFormat,
    onFormatChange: (ImageFormat) -> Unit,
    keepExif: Boolean,
    onKeepExifChange: (Boolean) -> Unit,
    estimatedBytes: Long,
    conversionHint: String?,
    enabled: Boolean
) {
    Surface(
        color = BookCard,
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(1.dp, BookLine),
        tonalElevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "压缩设置",
                color = BookInk,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "压缩方式", color = BookMuted, fontSize = 12.sp)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FormatChip(
                        label = "目标大小",
                        selected = mode == CompressionMode.TARGET_SIZE,
                        onClick = { onModeChange(CompressionMode.TARGET_SIZE) },
                        enabled = enabled
                    )
                    FormatChip(
                        label = "压缩比例",
                        selected = mode == CompressionMode.RATIO,
                        onClick = { onModeChange(CompressionMode.RATIO) },
                        enabled = enabled
                    )
                }
                if (mode == CompressionMode.TARGET_SIZE) {
                    Text(text = "目标大小（MB）", color = BookMuted, fontSize = 12.sp)
                    OutlinedTextField(
                        value = targetSizeInput,
                        onValueChange = { onTargetSizeChange(it) },
                        enabled = enabled,
                        label = { Text(text = "例如 2.0") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "目标大小优先生效",
                        color = BookMuted,
                        fontSize = 12.sp
                    )
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "压缩比例", color = BookMuted, fontSize = 12.sp)
                        Text(text = "$ratioPercent%", color = BookInk, fontWeight = FontWeight.SemiBold)
                    }
                    Slider(
                        value = ratio,
                        onValueChange = onRatioChange,
                        valueRange = 0.1f..0.9f,
                        enabled = enabled
                    )
                }
                Text(
                    text = "预计大小 ${formatBytes(estimatedBytes)}",
                    color = BookMuted,
                    fontSize = 12.sp
                )
                conversionHint?.let {
                    Text(text = it, color = BookMuted, fontSize = 12.sp)
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "质量上限", color = BookMuted, fontSize = 12.sp)
                    Text(text = "$qualityMax", color = BookInk, fontWeight = FontWeight.SemiBold)
                }
                Slider(
                    value = qualityMax.toFloat(),
                    onValueChange = { onQualityMaxChange(it.roundToInt().coerceIn(35, 95)) },
                    valueRange = 35f..95f,
                    enabled = enabled
                )
                Text(
                    text = "上限越低，体积越小",
                    color = BookMuted,
                    fontSize = 12.sp
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "输出格式", color = BookMuted, fontSize = 12.sp)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FormatChip(
                        label = "JPEG",
                        selected = outputFormat == ImageFormat.JPEG,
                        onClick = { onFormatChange(ImageFormat.JPEG) },
                        enabled = enabled
                    )
                    FormatChip(
                        label = "WebP",
                        selected = outputFormat == ImageFormat.WEBP,
                        onClick = { onFormatChange(ImageFormat.WEBP) },
                        enabled = enabled
                    )
                    FormatChip(
                        label = "PNG",
                        selected = outputFormat == ImageFormat.PNG,
                        onClick = { onFormatChange(ImageFormat.PNG) },
                        enabled = enabled
                    )
                }
                if (outputFormat == ImageFormat.PNG) {
                    Text(
                        text = "PNG 为无损格式，体积主要靠缩放，目标可能无法严格命中",
                        color = BookMuted,
                        fontSize = 12.sp
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "保留 EXIF", color = BookInk, fontWeight = FontWeight.SemiBold)
                    Text(text = "时间/位置信息", color = BookMuted, fontSize = 12.sp)
                }
                Switch(
                    checked = keepExif,
                    onCheckedChange = { onKeepExifChange(it) },
                    enabled = enabled
                )
            }
            if (!keepExif) {
                Text(
                    text = "关闭后将不保留拍摄时间与位置信息",
                    color = BookMuted,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun FormatChip(label: String, selected: Boolean, onClick: () -> Unit, enabled: Boolean) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        enabled = enabled,
        label = { Text(text = label) },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = BookSeal,
            selectedLabelColor = Color.White,
            containerColor = BookMist,
            labelColor = BookInk
        )
    )
}

@Composable
private fun CompressionResultCard(
    status: CompressionStatus,
    targetBytes: Long?,
    estimatedBytes: Long,
    compressedBytes: Long,
    enabled: Boolean,
    isProcessing: Boolean,
    message: String?,
    errorMessage: String?,
    validationMessage: String?,
    outputFormat: ImageFormat,
    compressionMode: CompressionMode,
    qualityMax: Int,
    previewBitmap: Bitmap?,
    targetMet: Boolean?,
    reachedQualityLimit: Boolean,
    reachedScaleLimit: Boolean,
    onCompress: () -> Unit,
    onSave: () -> Unit
) {
    val statusText = when (status) {
        CompressionStatus.IDLE -> "等待选择图片"
        CompressionStatus.READY -> "待压缩"
        CompressionStatus.COMPRESSED -> "已完成"
    }
    val outputText = if (status == CompressionStatus.COMPRESSED) {
        formatBytes(compressedBytes)
    } else {
        "未生成"
    }
    val targetText = targetBytes?.let { formatBytes(it) } ?: "未设置"
    val hitTarget = targetBytes?.let { compressedBytes in 1..it } ?: targetMet == true

    Surface(
        color = BookCard,
        shape = RoundedCornerShape(22.dp),
        border = BorderStroke(1.dp, BookLine),
        tonalElevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "压缩结果",
                    color = BookInk,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Text(text = statusText, color = BookMuted, fontSize = 12.sp)
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                ResultRow(label = "目标大小", value = targetText)
                ResultRow(label = "预计大小", value = formatBytes(estimatedBytes))
                ResultRow(label = "压缩后大小", value = outputText)
            }
            if (status == CompressionStatus.COMPRESSED) {
                val tip = when {
                    targetBytes == null && compressionMode == CompressionMode.RATIO ->
                        "按比例压缩，未设置目标大小"
                    targetBytes == null -> "未设置目标大小"
                    hitTarget == true -> "已达标"
                    outputFormat == ImageFormat.PNG && reachedScaleLimit ->
                        "未达标：已到最小分辨率，目标可能不可达"
                    outputFormat == ImageFormat.PNG ->
                        "未达标：建议降低分辨率或改用 JPEG/WebP"
                    reachedQualityLimit && reachedScaleLimit ->
                        "未达标：已到最低质量/最小分辨率，目标可能不可达"
                    reachedQualityLimit ->
                        "未达标：已到最低质量，目标可能不可达"
                    reachedScaleLimit ->
                        "未达标：已到最小分辨率，目标可能不可达"
                    else -> "未达标：可降低质量上限(当前 $qualityMax) 或降低分辨率"
                }
                Text(text = tip, color = BookMuted, fontSize = 12.sp)
            }
            if (previewBitmap != null) {
                Text(text = "压缩后预览", color = BookMuted, fontSize = 12.sp)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .border(1.dp, BookLine, RoundedCornerShape(16.dp))
                        .background(BookMist)
                ) {
                    Image(
                        bitmap = previewBitmap.asImageBitmap(),
                        contentDescription = "压缩后预览",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            if (isProcessing) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = BookSeal,
                    trackColor = BookLine
                )
            }
            validationMessage?.let {
                Text(text = it, color = BookSeal, fontSize = 12.sp)
            }
            message?.let {
                Text(text = it, color = BookMuted, fontSize = 12.sp)
            }
            errorMessage?.let {
                Text(text = it, color = BookSeal, fontSize = 12.sp)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = onCompress,
                    enabled = enabled,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BookSeal,
                        contentColor = Color.White,
                        disabledContainerColor = BookLine,
                        disabledContentColor = BookMuted
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "开始压缩")
                }
                Button(
                    onClick = onSave,
                    enabled = status == CompressionStatus.COMPRESSED && !isProcessing,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BookInk,
                        contentColor = BookCard,
                        disabledContainerColor = BookLine,
                        disabledContentColor = BookMuted
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "保存图片")
                }
            }
        }
    }
}

@Composable
private fun ResultRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, color = BookMuted, fontSize = 12.sp)
        Text(text = value, color = BookInk, fontWeight = FontWeight.SemiBold)
    }
}

private fun selectMode(state: PomodoroState, mode: TimerMode) {
    state.selectedMode = TimerMode.COUNTDOWN
    if (state.timerStatus == TimerStatus.IDLE || state.timerStatus == TimerStatus.FINISHED) {
        syncIdleTimer(state)
    }
}

private fun syncIdleTimer(state: PomodoroState) {
    state.activeMode = TimerMode.COUNTDOWN
    state.selectedMode = TimerMode.COUNTDOWN
    state.sessionTotalMillis = state.countdownMinutes * 60L * 1000L
    state.baseElapsedMillis = 0L
    state.baseRemainingMillis = state.sessionTotalMillis
    state.elapsedMillis = 0L
    state.remainingMillis = state.sessionTotalMillis
}

private fun applyCountdownMinutes(state: PomodoroState, minutes: Int) {
    val safeMinutes = minutes.coerceIn(1, 240)
    state.countdownMinutes = safeMinutes
    if (state.timerStatus == TimerStatus.IDLE || state.timerStatus == TimerStatus.FINISHED) {
        state.timerStatus = TimerStatus.IDLE
        syncIdleTimer(state)
    }
}

private fun startNewSession(context: Context, state: PomodoroState, task: TaskItem? = null) {
    state.activeTaskId = -1
    state.activeTaskTitle = ""
    state.activeMode = TimerMode.COUNTDOWN
    state.selectedMode = TimerMode.COUNTDOWN
    state.sessionTotalMillis = state.countdownMinutes * 60L * 1000L
    state.elapsedMillis = 0L
    state.remainingMillis = state.sessionTotalMillis
    state.baseRemainingMillis = state.remainingMillis
    state.baseElapsedMillis = 0L
    state.startTimestamp = SystemClock.elapsedRealtime()
    state.timerStatus = TimerStatus.RUNNING
    schedulePomodoroAlarm(context, state)
}

private fun resumeSession(context: Context, state: PomodoroState) {
    if (state.timerStatus != TimerStatus.PAUSED) return
    state.startTimestamp = SystemClock.elapsedRealtime()
    if (state.activeMode == TimerMode.COUNT_UP) {
        state.baseElapsedMillis = state.elapsedMillis
    } else {
        state.baseRemainingMillis = state.remainingMillis
    }
    state.timerStatus = TimerStatus.RUNNING
    schedulePomodoroAlarm(context, state)
}

private fun pauseSession(context: Context, state: PomodoroState) {
    if (state.timerStatus != TimerStatus.RUNNING) return
    updateTimerProgress(state)
    state.timerStatus = TimerStatus.PAUSED
    cancelPomodoroAlarm(context)
}

private fun resetSession(context: Context, state: PomodoroState) {
    state.timerStatus = TimerStatus.IDLE
    state.activeTaskId = -1
    state.activeTaskTitle = ""
    cancelPomodoroAlarm(context)
    syncIdleTimer(state)
}

private fun finishSession(state: PomodoroState, completeTask: Boolean) {
    if (state.timerStatus == TimerStatus.RUNNING) {
        updateTimerProgress(state)
    }
    val elapsed = (state.sessionTotalMillis - state.remainingMillis).coerceAtLeast(0L)
    addFocusTime(state, elapsed)
    state.activeTaskId = -1
    state.activeTaskTitle = ""
    state.remainingMillis = 0L
    state.timerStatus = TimerStatus.FINISHED
}

private fun updateTimerProgress(state: PomodoroState) {
    val now = SystemClock.elapsedRealtime()
    val delta = now - state.startTimestamp
    if (state.activeMode == TimerMode.COUNT_UP) {
        state.elapsedMillis = state.baseElapsedMillis + delta
    } else {
        val remaining = (state.baseRemainingMillis - delta).coerceAtLeast(0L)
        state.remainingMillis = remaining
        state.elapsedMillis = (state.sessionTotalMillis - remaining).coerceAtLeast(0L)
    }
}

private fun handleCountdownFinished(state: PomodoroState) {
    addFocusTime(state, state.sessionTotalMillis)
    state.activeTaskId = -1
    state.activeTaskTitle = ""
    state.remainingMillis = 0L
}

private fun triggerPomodoroFinishAlerts(context: Context, state: PomodoroState) {
    if (state.enableFinishVibrate) {
        vibrateForPomodoroFinish(context)
    }
    if (state.enableFinishNotification) {
        sendPomodoroFinishNotification(context, state)
    }
}

private fun sendPomodoroFinishNotification(context: Context, state: PomodoroState) {
    if (!canPostNotifications(context)) return
    createPomodoroNotificationChannels(context)
    val (title, message) = buildPomodoroFinishContent(state)
    val channelId = POMODORO_CHANNEL_ALERT
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        pendingIntentFlags()
    )
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
        .setContentTitle(title)
        .setContentText(message)
        .setStyle(NotificationCompat.BigTextStyle().bigText(message))
        .setPriority(NotificationCompat.PRIORITY_MAX)
        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        .setCategory(NotificationCompat.CATEGORY_ALARM)
        .setContentIntent(pendingIntent)
        .setFullScreenIntent(pendingIntent, true)
        .setAutoCancel(true)
        .setOnlyAlertOnce(true)
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
        builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
        if (state.enableFinishVibrate) {
            builder.setVibrate(longArrayOf(0, 120, 80, 180))
        }
    }
    val notification = builder.build()
    NotificationManagerCompat.from(context).notify(POMODORO_NOTIFICATION_ID, notification)
}

private fun buildPomodoroFinishContent(state: PomodoroState): Pair<String, String> {
    val totalMinutes = (state.sessionTotalMillis / 60000L).toInt().coerceAtLeast(1)
    return when (state.activeMode) {
        TimerMode.POMODORO -> {
            "番茄完成" to "${phaseLabel(state.pomodoroPhase)}结束，休息一下"
        }
        TimerMode.COUNTDOWN -> {
            "倒计时结束" to "已完成 ${totalMinutes} 分钟倒计时"
        }
        TimerMode.COUNT_UP -> {
            "计时完成" to "已完成 ${totalMinutes} 分钟"
        }
    }
}

private fun vibrateForPomodoroFinish(context: Context) {
    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val manager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        manager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }
    if (!vibrator.hasVibrator()) return
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val effect = VibrationEffect.createWaveform(longArrayOf(0, 120, 80, 180), -1)
        vibrator.vibrate(effect)
    } else {
        @Suppress("DEPRECATION")
        vibrator.vibrate(longArrayOf(0, 120, 80, 180), -1)
    }
}

private fun createPomodoroNotificationChannels(context: Context) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
    val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val alertChannel = NotificationChannel(
        POMODORO_CHANNEL_ALERT,
        "番茄完成提醒",
        NotificationManager.IMPORTANCE_HIGH
    ).apply {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        setSound(Settings.System.DEFAULT_NOTIFICATION_URI, audioAttributes)
        enableVibration(false)
    }
    val silentChannel = NotificationChannel(
        POMODORO_CHANNEL_SILENT,
        "番茄完成提醒（静音）",
        NotificationManager.IMPORTANCE_HIGH
    ).apply {
        enableVibration(false)
        setSound(null, null)
    }
    manager.createNotificationChannel(alertChannel)
    manager.createNotificationChannel(silentChannel)
}

private fun canPostNotifications(context: Context): Boolean {
    if (!NotificationManagerCompat.from(context).areNotificationsEnabled()) return false
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    } else {
        true
    }
}

private fun canPomodoroHeadsUp(context: Context, channelId: String): Boolean {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return true
    val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val channel = manager.getNotificationChannel(channelId) ?: return false
    return channel.importance >= NotificationManager.IMPORTANCE_HIGH
}

private fun openPomodoroNotificationSettings(context: Context) {
    val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS).apply {
            putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
            putExtra(Settings.EXTRA_CHANNEL_ID, POMODORO_CHANNEL_ALERT)
        }
    } else {
        Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
            putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
        }
    }
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
}

private fun pendingIntentFlags(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    } else {
        PendingIntent.FLAG_UPDATE_CURRENT
    }
}

private fun completeTaskById(state: PomodoroState, taskId: Int) {
    val index = state.tasks.indexOfFirst { it.id == taskId }
    if (index == -1) return
    val task = state.tasks[index]
    if (task.completed) return
    state.tasks[index] = task.copy(completed = true)
    state.completedTasks += 1
}

private fun addFocusTime(state: PomodoroState, millis: Long) {
    if (millis <= 0L) return
    state.totalFocusMillisToday += millis
    state.totalFocusMillisWeek += millis
}

private fun phaseDurationMillis(state: PomodoroState): Long {
    val minutes = when (state.pomodoroPhase) {
        PomodoroPhase.WORK -> state.workMinutes
        PomodoroPhase.SHORT_BREAK -> state.shortBreakMinutes
        PomodoroPhase.LONG_BREAK -> state.longBreakMinutes
    }
    return minutes * 60L * 1000L
}

private fun modeLabel(mode: TimerMode): String {
    return when (mode) {
        TimerMode.POMODORO -> "番茄"
        TimerMode.COUNTDOWN -> "倒计时"
        TimerMode.COUNT_UP -> "计时"
    }
}

private fun phaseLabel(phase: PomodoroPhase): String {
    return when (phase) {
        PomodoroPhase.WORK -> "专注时段"
        PomodoroPhase.SHORT_BREAK -> "短休时段"
        PomodoroPhase.LONG_BREAK -> "长休时段"
    }
}

private fun formatTimer(millis: Long): String {
    val totalSeconds = millis / 1000L
    val hours = totalSeconds / 3600L
    val minutes = (totalSeconds % 3600L) / 60L
    val seconds = totalSeconds % 60L
    return if (hours > 0) {
        "%02d:%02d:%02d".format(hours, minutes, seconds)
    } else {
        "%02d:%02d".format(minutes, seconds)
    }
}

private fun formatDuration(millis: Long): String {
    val totalMinutes = millis / 60000L
    val hours = totalMinutes / 60L
    val minutes = totalMinutes % 60L
    return if (hours > 0) {
        "${hours}小时${minutes}分钟"
    } else {
        "${minutes}分钟"
    }
}

private fun formatBytes(bytes: Long): String {
    if (bytes <= 0L) return "--"
    val kb = bytes / 1024f
    if (kb < 1024f) return String.format("%.1f KB", kb)
    val mb = kb / 1024f
    if (mb < 1024f) return String.format("%.2f MB", mb)
    val gb = mb / 1024f
    return String.format("%.2f GB", gb)
}

private fun parseTargetSizeMb(input: String): Long? {
    val value = input.trim().toDoubleOrNull() ?: return null
    if (value <= 0.0) return null
    val bytes = value * 1024.0 * 1024.0
    return bytes.toLong().coerceAtLeast(1L)
}

private fun mimeLabel(mimeType: String): String {
    return when {
        mimeType.contains("jpeg", ignoreCase = true) -> "JPEG"
        mimeType.contains("jpg", ignoreCase = true) -> "JPEG"
        mimeType.contains("png", ignoreCase = true) -> "PNG"
        mimeType.contains("webp", ignoreCase = true) -> "WebP"
        else -> "未知"
    }
}

private fun readImageMeta(context: Context, uri: Uri): ImageMeta {
    var name = "已选择图片"
    var size = 0L
    context.contentResolver.query(
        uri,
        arrayOf(OpenableColumns.DISPLAY_NAME, OpenableColumns.SIZE),
        null,
        null,
        null
    )?.use { cursor ->
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
        if (cursor.moveToFirst()) {
            if (nameIndex >= 0) {
                name = cursor.getString(nameIndex) ?: name
            }
            if (sizeIndex >= 0) {
                size = cursor.getLong(sizeIndex)
            }
        }
    }
    val options = BitmapFactory.Options().apply { inJustDecodeBounds = true }
    context.contentResolver.openInputStream(uri)?.use { stream ->
        BitmapFactory.decodeStream(stream, null, options)
    }
    val mimeType = context.contentResolver.getType(uri) ?: "image/*"
    return ImageMeta(
        uri = uri,
        displayName = name,
        sizeBytes = size,
        width = options.outWidth,
        height = options.outHeight,
        mimeType = mimeType
    )
}

private fun loadPreviewBitmap(context: Context, uri: Uri, maxSize: Int): Bitmap? {
    val bitmap = decodeBitmap(context, uri, maxDimension = maxSize) ?: return null
    return applyExifOrientation(context, uri, bitmap)
}

private fun decodePreviewFromBytes(data: ByteArray, maxSize: Int): Bitmap? {
    val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
    BitmapFactory.decodeByteArray(data, 0, data.size, bounds)
    if (bounds.outWidth <= 0 || bounds.outHeight <= 0) return null
    val sampleSize = calculateInSampleSize(bounds.outWidth, bounds.outHeight, maxSize, maxSize)
    val options = BitmapFactory.Options().apply {
        inSampleSize = sampleSize
        inPreferredConfig = Bitmap.Config.ARGB_8888
    }
    return BitmapFactory.decodeByteArray(data, 0, data.size, options)
}

private fun compressImage(
    context: Context,
    meta: ImageMeta,
    ratio: Float,
    targetBytesOverride: Long?,
    format: ImageFormat,
    qualityMax: Int
): CompressionOutcome? {
    return try {
        val targetBytes = targetBytesOverride
            ?: if (meta.sizeBytes > 0L) (meta.sizeBytes * ratio).toLong() else 0L
        val bitmap = decodeBitmap(context, meta.uri, maxDimension = 4096) ?: return null
        val oriented = applyExifOrientation(context, meta.uri, bitmap)
        compressToTarget(oriented, format, targetBytes, ratio, qualityMax)
    } catch (_: Exception) {
        null
    }
}

private fun decodeBitmap(context: Context, uri: Uri, maxDimension: Int): Bitmap? {
    val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
    context.contentResolver.openInputStream(uri)?.use { stream ->
        BitmapFactory.decodeStream(stream, null, bounds)
    }
    if (bounds.outWidth <= 0 || bounds.outHeight <= 0) return null
    val sampleSize = calculateInSampleSize(bounds.outWidth, bounds.outHeight, maxDimension, maxDimension)
    val options = BitmapFactory.Options().apply {
        inSampleSize = sampleSize
        inPreferredConfig = Bitmap.Config.ARGB_8888
    }
    return context.contentResolver.openInputStream(uri)?.use { stream ->
        BitmapFactory.decodeStream(stream, null, options)
    }
}

private fun calculateInSampleSize(
    width: Int,
    height: Int,
    reqWidth: Int,
    reqHeight: Int
): Int {
    var inSampleSize = 1
    var halfWidth = width / 2
    var halfHeight = height / 2
    while (halfWidth / inSampleSize >= reqWidth && halfHeight / inSampleSize >= reqHeight) {
        inSampleSize *= 2
    }
    return inSampleSize.coerceAtLeast(1)
}

private fun applyExifOrientation(context: Context, uri: Uri, bitmap: Bitmap): Bitmap {
    val orientation = context.contentResolver.openInputStream(uri)?.use { stream ->
        ExifInterface(stream).getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )
    } ?: ExifInterface.ORIENTATION_NORMAL

    val matrix = Matrix()
    when (orientation) {
        ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> matrix.setScale(-1f, 1f)
        ExifInterface.ORIENTATION_ROTATE_180 -> matrix.setRotate(180f)
        ExifInterface.ORIENTATION_FLIP_VERTICAL -> {
            matrix.setScale(1f, -1f)
        }
        ExifInterface.ORIENTATION_TRANSPOSE -> {
            matrix.setRotate(90f)
            matrix.postScale(-1f, 1f)
        }
        ExifInterface.ORIENTATION_ROTATE_90 -> matrix.setRotate(90f)
        ExifInterface.ORIENTATION_TRANSVERSE -> {
            matrix.setRotate(-90f)
            matrix.postScale(-1f, 1f)
        }
        ExifInterface.ORIENTATION_ROTATE_270 -> matrix.setRotate(-90f)
        else -> return bitmap
    }
    val rotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    if (rotated != bitmap) {
        bitmap.recycle()
    }
    return rotated
}

private fun compressToTarget(
    bitmap: Bitmap,
    format: ImageFormat,
    targetBytes: Long,
    ratio: Float,
    qualityMax: Int
): CompressionOutcome {
    val compressFormat = bitmapCompressFormat(format)
    val cappedQualityMax = qualityMax.coerceIn(35, 95)
    val maxScaleAttempts = 6
    var currentBitmap = bitmap
    if (targetBytes <= 0L) {
        val quality = (ratio * 100f).roundToInt().coerceIn(35, cappedQualityMax)
        val data = if (format == ImageFormat.PNG) {
            val scale = kotlin.math.sqrt(ratio.toDouble()).toFloat().coerceIn(0.35f, 0.95f)
            val scaled = scaleBitmap(currentBitmap, scale)
            compressBitmap(scaled, compressFormat, 100)
        } else {
            compressBitmap(currentBitmap, compressFormat, quality)
        }
        return CompressionOutcome(
            data = data,
            metTarget = true,
            reachedQualityLimit = false,
            reachedScaleLimit = false
        )
    }
    var bestData = compressBitmap(currentBitmap, compressFormat, 90)
    var reachedQualityLimit = false
    var reachedScaleLimit = false
    var metTarget = false
    var attempts = 0
    while (attempts <= maxScaleAttempts) {
        val result = if (format == ImageFormat.PNG) {
            val data = compressBitmap(currentBitmap, compressFormat, 100)
            QualitySearchResult(
                data = data,
                metTarget = data.size <= targetBytes,
                reachedMinQuality = false
            )
        } else {
            compressWithQualitySearch(currentBitmap, compressFormat, targetBytes, cappedQualityMax)
        }
        bestData = result.data
        if (result.metTarget) {
            metTarget = true
            break
        } else if (result.reachedMinQuality) {
            reachedQualityLimit = true
        }
        if (attempts >= maxScaleAttempts) {
            reachedScaleLimit = true
            break
        }
        val scaled = scaleBitmap(currentBitmap, 0.9f)
        if (scaled == currentBitmap) {
            reachedScaleLimit = true
            break
        }
        currentBitmap = scaled
        attempts += 1
    }
    return CompressionOutcome(
        data = bestData,
        metTarget = metTarget,
        reachedQualityLimit = reachedQualityLimit,
        reachedScaleLimit = reachedScaleLimit
    )
}

private fun compressWithQualitySearch(
    bitmap: Bitmap,
    format: Bitmap.CompressFormat,
    targetBytes: Long,
    qualityMax: Int
): QualitySearchResult {
    val cappedQualityMax = qualityMax.coerceIn(35, 95)
    var low = 35
    var high = cappedQualityMax
    var best: ByteArray? = null
    while (low <= high) {
        val mid = (low + high) / 2
        val data = compressBitmap(bitmap, format, mid)
        if (data.size > targetBytes) {
            high = mid - 1
        } else {
            best = data
            low = mid + 1
        }
    }
    return if (best != null) {
        QualitySearchResult(
            data = best,
            metTarget = true,
            reachedMinQuality = false
        )
    } else {
        QualitySearchResult(
            data = compressBitmap(bitmap, format, 35),
            metTarget = false,
            reachedMinQuality = true
        )
    }
}

private fun compressBitmap(
    bitmap: Bitmap,
    format: Bitmap.CompressFormat,
    quality: Int
): ByteArray {
    val output = ByteArrayOutputStream()
    bitmap.compress(format, quality.coerceIn(0, 100), output)
    return output.toByteArray()
}

private fun scaleBitmap(bitmap: Bitmap, scale: Float): Bitmap {
    val newWidth = (bitmap.width * scale).roundToInt().coerceAtLeast(1)
    val newHeight = (bitmap.height * scale).roundToInt().coerceAtLeast(1)
    if (newWidth == bitmap.width && newHeight == bitmap.height) return bitmap
    return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
}

private fun bitmapCompressFormat(format: ImageFormat): Bitmap.CompressFormat {
    return when (format) {
        ImageFormat.JPEG -> Bitmap.CompressFormat.JPEG
        ImageFormat.WEBP -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                Bitmap.CompressFormat.WEBP_LOSSY
            } else {
                Bitmap.CompressFormat.WEBP
            }
        }
        ImageFormat.PNG -> Bitmap.CompressFormat.PNG
    }
}

private fun outputMimeType(format: ImageFormat): String {
    return when (format) {
        ImageFormat.JPEG -> "image/jpeg"
        ImageFormat.WEBP -> "image/webp"
        ImageFormat.PNG -> "image/png"
    }
}

private fun outputExtension(format: ImageFormat): String {
    return when (format) {
        ImageFormat.JPEG -> "jpg"
        ImageFormat.WEBP -> "webp"
        ImageFormat.PNG -> "png"
    }
}

private fun buildOutputName(displayName: String, format: ImageFormat): String {
    val base = displayName.substringBeforeLast('.', displayName)
    return "${base}_compressed.${outputExtension(format)}"
}

private fun saveCompressedImage(
    context: Context,
    sourceUri: Uri,
    displayName: String,
    data: ByteArray,
    format: ImageFormat,
    keepExif: Boolean
): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        saveWithMediaStore(context, sourceUri, displayName, data, format, keepExif)
    } else {
        saveToAppPictures(context, sourceUri, displayName, data, format, keepExif)
    }
}

private fun saveWithMediaStore(
    context: Context,
    sourceUri: Uri,
    displayName: String,
    data: ByteArray,
    format: ImageFormat,
    keepExif: Boolean
): Boolean {
    return try {
        val resolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, buildOutputName(displayName, format))
            put(MediaStore.Images.Media.MIME_TYPE, outputMimeType(format))
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/小白的工具箱")
            put(MediaStore.Images.Media.IS_PENDING, 1)
        }
        val collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        val itemUri = resolver.insert(collection, contentValues) ?: return false
        resolver.openOutputStream(itemUri)?.use { stream ->
            stream.write(data)
            stream.flush()
        } ?: return false
        if (keepExif) {
            copyExif(context, sourceUri, itemUri)
        }
        contentValues.clear()
        contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
        resolver.update(itemUri, contentValues, null, null)
        true
    } catch (_: Exception) {
        false
    }
}

private fun saveToAppPictures(
    context: Context,
    sourceUri: Uri,
    displayName: String,
    data: ByteArray,
    format: ImageFormat,
    keepExif: Boolean
): Boolean {
    return try {
        val root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val parent = File(root, "小白的工具箱")
        if (!parent.exists()) {
            parent.mkdirs()
        }
        val file = File(parent, buildOutputName(displayName, format))
        FileOutputStream(file).use { output ->
            output.write(data)
        }
        if (keepExif) {
            copyExifToFile(context, sourceUri, file)
        }
        MediaScannerConnection.scanFile(
            context,
            arrayOf(file.absolutePath),
            arrayOf(outputMimeType(format)),
            null
        )
        true
    } catch (_: Exception) {
        false
    }
}

private fun copyExif(context: Context, sourceUri: Uri, targetUri: Uri) {
    val source = context.contentResolver.openInputStream(sourceUri)?.use { stream ->
        ExifInterface(stream)
    } ?: return
    context.contentResolver.openFileDescriptor(targetUri, "rw")?.use { pfd ->
        val target = ExifInterface(pfd.fileDescriptor)
        for (tag in EXIF_TAGS_TO_COPY) {
            val value = source.getAttribute(tag)
            if (value != null) {
                target.setAttribute(tag, value)
            }
        }
        target.setAttribute(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL.toString()
        )
        target.saveAttributes()
    }
}

private fun copyExifToFile(context: Context, sourceUri: Uri, file: File) {
    val source = context.contentResolver.openInputStream(sourceUri)?.use { stream ->
        ExifInterface(stream)
    } ?: return
    val target = ExifInterface(file.absolutePath)
    for (tag in EXIF_TAGS_TO_COPY) {
        val value = source.getAttribute(tag)
        if (value != null) {
            target.setAttribute(tag, value)
        }
    }
    target.setAttribute(
        ExifInterface.TAG_ORIENTATION,
        ExifInterface.ORIENTATION_NORMAL.toString()
    )
    target.saveAttributes()
}

private val EXIF_TAGS_TO_COPY = listOf(
    "DateTime",
    "DateTimeOriginal",
    "DateTimeDigitized",
    "GPSLatitude",
    "GPSLatitudeRef",
    "GPSLongitude",
    "GPSLongitudeRef",
    "GPSAltitude",
    "GPSAltitudeRef",
    "GPSTimeStamp",
    "GPSDateStamp",
    "Make",
    "Model"
)

private val PomodoroGreenDeep = Color(0xFF18332E)
private val PomodoroGreen = Color(0xFF2F4B3D)
private val PomodoroPaper = Color(0xFFF6F0E6)
private val PomodoroInk = Color(0xFF2B2A26)
private val PomodoroSeal = Color(0xFFC85A2E)
private val PomodoroMuted = Color(0xFF7F7768)
private val PomodoroMutedLight = Color(0xFFBDB4A6)
private val PomodoroLine = Color(0xFFD8CFC0)

private val BookBgDeep = Color(0xFFE6D6C3)
private val BookBgLight = Color(0xFFF7F1E7)
private val BookCard = Color(0xFFFBF6EF)
private val BookInk = Color(0xFF3B2F27)
private val BookSeal = Color(0xFFC46A3B)
private val BookMuted = Color(0xFF8A7B6C)
private val BookLine = Color(0xFFD8C8B5)
private val BookMist = Color(0xFFF4ECE2)

private val MarqueeNight = Color(0xFF0B0F13)
private val MarqueeDeep = Color(0xFF121921)
private val MarqueePanel = Color(0xFF0F151B)
private val MarqueePanelAlt = Color(0xFF1A2027)
private val MarqueeLine = Color(0xFF27313A)
private val MarqueeText = Color(0xFFE9F1F6)
private val MarqueeMuted = Color(0xFF8FA1AE)
private val MarqueeNeon = Color(0xFF33F0B5)

private val CChatBgTop = Color(0xFFF6F8F7)
private val CChatBgBottom = Color(0xFFEFF3F4)
private val CChatSurface = Color(0xFFFFFFFF)
private val CChatCard = Color(0xFFF8FAFB)
private val CChatLine = Color(0xFFDDE5EA)
private val CChatText = Color(0xFF1D2B2F)
private val CChatMuted = Color(0xFF6B7A83)
private val CChatAccent = Color(0xFF18A07E)
private val CChatAccentSoft = Color(0xFFE4F6F1)
private val CChatUserBubble = Color(0xFF16312C)
private val CChatAssistantBubble = Color(0xFFFFFFFF)
private val CChatUserText = Color(0xFFF1FFF9)
private val CChatAssistantText = Color(0xFF1D2B2F)
private val CChatCopyBg = Color(0xFFF2F5F6)
private val CChatCopyText = Color(0xFF3C4A52)
private val CChatCopyBorder = Color(0xFFD6E0E6)
private val CChatCursor = Color(0xFF18A07E)
private val CChatCodeBg = Color(0xFF122028)
private val CChatCodeText = Color(0xFFE7F2F7)
private val CChatCodeBorder = Color(0xFF1F2E36)

private val CChatMarkdownPalette = MarkdownPalette(
    accent = CChatAccent,
    muted = CChatMuted,
    quoteBg = CChatCopyBg,
    quoteBorder = CChatCopyBorder,
    tableSurface = CChatSurface,
    tableHeaderBg = CChatCopyBg,
    tableBorder = CChatLine,
    codeBg = CChatCodeBg,
    codeBorder = CChatCodeBorder,
    codeText = CChatCodeText,
    cursor = CChatCursor
)

private val CChatBubblePalette = ChatBubblePalette(
    userBubble = CChatUserBubble,
    assistantBubble = CChatAssistantBubble,
    userText = CChatUserText,
    assistantText = CChatAssistantText,
    line = CChatLine,
    copyBg = CChatCopyBg,
    copyBorder = CChatCopyBorder,
    copyText = CChatCopyText,
    accent = CChatAccent,
    accentSoft = CChatAccentSoft,
    muted = CChatMuted
)

private val DsbDisplayFont = FontFamily(
    Font(R.font.bodoni_moda_bold, FontWeight.Bold),
    Font(R.font.bodoni_moda_semibold, FontWeight.SemiBold)
)
private val DsbBodyFont = FontFamily(
    Font(R.font.assistant_regular, FontWeight.Normal),
    Font(R.font.assistant_semibold, FontWeight.SemiBold)
)

private val DsbBgTop = Color(0xFFFFF2E9)
private val DsbBgBottom = Color(0xFFFFFBF7)
private val DsbSurface = Color(0xFFFFFFFF)
private val DsbCard = Color(0xFFFFF7F2)
private val DsbLine = Color(0xFFEBDCD2)
private val DsbText = Color(0xFF3E2D22)
private val DsbMuted = Color(0xFF8B6F61)
private val DsbAccent = Color(0xFFE68A5C)
private val DsbAccentSoft = Color(0xFFFFE3D4)
private val DsbUserBubble = Color(0xFFF2C9AF)
private val DsbAssistantBubble = Color(0xFFFFFFFF)
private val DsbUserText = Color(0xFF3B2419)
private val DsbAssistantText = Color(0xFF3E2D22)
private val DsbCopyBg = Color(0xFFFFF1E8)
private val DsbCopyText = Color(0xFF6F5547)
private val DsbCopyBorder = Color(0xFFEAD3C6)
private val DsbCursor = Color(0xFFE68A5C)
private val DsbCodeBg = Color(0xFF2B1E1A)
private val DsbCodeText = Color(0xFFFFF3E8)
private val DsbCodeBorder = Color(0xFF3A2A24)

private val DsbMarkdownPalette = MarkdownPalette(
    accent = DsbAccent,
    muted = DsbMuted,
    quoteBg = DsbCopyBg,
    quoteBorder = DsbCopyBorder,
    tableSurface = DsbSurface,
    tableHeaderBg = DsbCopyBg,
    tableBorder = DsbLine,
    codeBg = DsbCodeBg,
    codeBorder = DsbCodeBorder,
    codeText = DsbCodeText,
    cursor = DsbCursor
)

private val DsbBubblePalette = ChatBubblePalette(
    userBubble = DsbUserBubble,
    assistantBubble = DsbAssistantBubble,
    userText = DsbUserText,
    assistantText = DsbAssistantText,
    line = DsbLine,
    copyBg = DsbCopyBg,
    copyBorder = DsbCopyBorder,
    copyText = DsbCopyText,
    accent = DsbAccent,
    accentSoft = DsbAccentSoft,
    muted = DsbMuted
)

@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun MemoryTreeScreen(
    onBack: () -> Unit
) {
    val context = LocalContext.current
    var showPermissionDialog by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        )
    }
    var pendingFileCallback by remember { mutableStateOf<ValueCallback<Array<Uri>>?>(null) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        showPermissionDialog = !granted
    }

    val fileChooserLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val callback = pendingFileCallback
        if (callback == null) return@rememberLauncherForActivityResult
        val uris = if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val clipData = data?.clipData
            when {
                clipData != null -> Array(clipData.itemCount) { index ->
                    clipData.getItemAt(index).uri
                }
                data?.data != null -> arrayOf(data.data!!)
                else -> null
            }
        } else {
            null
        }
        callback.onReceiveValue(uris)
        pendingFileCallback = null
    }

    val webView = remember {
        WebView(context).apply {
            setBackgroundColor(android.graphics.Color.BLACK)
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.allowFileAccess = true
            settings.allowContentAccess = true
            settings.mediaPlaybackRequiresUserGesture = false
            settings.allowFileAccessFromFileURLs = false
            settings.allowUniversalAccessFromFileURLs = false
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            webViewClient = object : WebViewClient() {
                override fun shouldInterceptRequest(
                    view: WebView,
                    request: WebResourceRequest
                ): WebResourceResponse? {
                    return resolveAssetRequest(context, request)
                }
            }
            webChromeClient = object : WebChromeClient() {
                override fun onPermissionRequest(request: PermissionRequest) {
                    val wantsCamera = request.resources.contains(
                        PermissionRequest.RESOURCE_VIDEO_CAPTURE
                    )
                    val granted = ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                    if (wantsCamera && granted) {
                        request.grant(arrayOf(PermissionRequest.RESOURCE_VIDEO_CAPTURE))
                    } else {
                        request.deny()
                        showPermissionDialog = true
                    }
                }

                override fun onShowFileChooser(
                    webView: WebView?,
                    filePathCallback: ValueCallback<Array<Uri>>?,
                    fileChooserParams: WebChromeClient.FileChooserParams?
                ): Boolean {
                    if (filePathCallback == null) return false
                    pendingFileCallback?.onReceiveValue(null)
                    pendingFileCallback = filePathCallback
                    val intent = fileChooserParams?.createIntent()
                    return try {
                        if (intent != null) {
                            fileChooserLauncher.launch(intent)
                            true
                        } else {
                            pendingFileCallback?.onReceiveValue(null)
                            pendingFileCallback = null
                            false
                        }
                    } catch (e: Exception) {
                        pendingFileCallback?.onReceiveValue(null)
                        pendingFileCallback = null
                        false
                    }
                }
            }
            loadUrl("https://appassets.androidplatform.net/assets/memory_tree/index.html")
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            webView.destroy()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(WindowInsets.navigationBars.asPaddingValues())
    ) {
        MemoryTreeTopBar(onBack = onBack)
        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier.weight(1f)) {
            AndroidView(
                factory = { webView },
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    if (showPermissionDialog) {
        AlertDialog(
            onDismissRequest = { showPermissionDialog = false },
            title = { Text(text = "需要摄像头权限") },
            text = {
                Text(text = "回忆圣诞树需要访问摄像头以识别手势，请授权后体验完整效果。")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showPermissionDialog = false
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                ) {
                    Text(text = "启用摄像头")
                }
            },
            dismissButton = {
                TextButton(onClick = { showPermissionDialog = false }) {
                    Text(text = "稍后")
                }
            }
        )
    }
}

@Composable
private fun MemoryTreeTopBar(onBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        TextButton(onClick = onBack) {
            Text(text = "返回", color = BookInk)
        }
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "回忆圣诞树",
                color = BookInk,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "手势控制 · 3D照片云",
                color = BookMuted,
                fontSize = 12.sp
            )
        }
    }
}

// ===== Alarm Manager helper functions =====
private fun schedulePomodoroAlarm(context: Context, state: PomodoroState) {
    if (state.activeMode == TimerMode.COUNT_UP) return // 计时模式不需要闹钟

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, PomodoroAlarmReceiver::class.java).apply {
        action = POMODORO_ALARM_ACTION
        putExtra("total_millis", state.sessionTotalMillis)
        putExtra("active_mode", state.activeMode.ordinal)
        putExtra("pomodoro_phase", state.pomodoroPhase.ordinal)
    }
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        POMODORO_ALARM_REQUEST_CODE,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    // 计算倒计时结束时间
    val finishTime = SystemClock.elapsedRealtime() + state.remainingMillis

    // 尝试设置精确闹钟，失败则降级
    try {
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            finishTime,
            pendingIntent
        )
    } catch (e: SecurityException) {
        // 降级使用非精确闹钟
        alarmManager.setAndAllowWhileIdle(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            finishTime,
            pendingIntent
        )
    }
}

private fun cancelPomodoroAlarm(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, PomodoroAlarmReceiver::class.java).apply {
        action = POMODORO_ALARM_ACTION
    }
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        POMODORO_ALARM_REQUEST_CODE,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
    alarmManager.cancel(pendingIntent)
}

private fun resolveAssetRequest(
    context: Context,
    request: WebResourceRequest
): WebResourceResponse? {
    val url = request.url
    if (url.host != "appassets.androidplatform.net") return null
    val rawPath = url.path ?: return null
    if (!rawPath.startsWith("/assets/")) return null
    val assetPath = rawPath.removePrefix("/assets/").trimStart('/')
    if (assetPath.isBlank() || assetPath.contains("..")) return null
    return try {
        val inputStream = context.assets.open(assetPath)
        val mimeType = guessMimeType(assetPath)
        val encoding = if (
            mimeType.startsWith("text/") ||
            mimeType == "application/javascript" ||
            mimeType == "application/json"
        ) {
            "utf-8"
        } else {
            null
        }
        WebResourceResponse(mimeType, encoding, inputStream)
    } catch (e: IOException) {
        null
    }
}

private fun guessMimeType(path: String): String {
    val extension = path.substringAfterLast('.', "").lowercase(Locale.US)
    if (extension.isBlank()) return "application/octet-stream"
    return when (extension) {
        "html" -> "text/html"
        "js" -> "application/javascript"
        "css" -> "text/css"
        "json" -> "application/json"
        "ttf" -> "font/ttf"
        "woff" -> "font/woff"
        "woff2" -> "font/woff2"
        "png" -> "image/png"
        "jpg", "jpeg" -> "image/jpeg"
        "svg" -> "image/svg+xml"
        "webp" -> "image/webp"
        "wasm" -> "application/wasm"
        "data", "tflite", "binarypb", "bin" -> "application/octet-stream"
        else -> MimeTypeMap.getSingleton()
            .getMimeTypeFromExtension(extension)
            ?: "application/octet-stream"
    }
}



