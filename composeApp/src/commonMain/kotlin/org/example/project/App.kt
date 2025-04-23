package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.example.project.PoppinsFontFamily
import org.example.project.PoppinsTypography
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.icon
import kotlinproject.composeapp.generated.resources.bg_image
import kotlinproject.composeapp.generated.resources.double_icon_1
import kotlinproject.composeapp.generated.resources.rightArrow
import kotlinproject.composeapp.generated.resources.plusCircle2
import kotlinproject.composeapp.generated.resources.icon_calendar
import kotlinproject.composeapp.generated.resources.icon_time
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import kotlinx.datetime.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.ViewModel

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Schedule : Routes("schedule")
}


@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    MaterialTheme(
        typography = PoppinsTypography()
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFF4F8FB)
        ) {
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    MainApp(navController, "")
                }

                composable("home/{note}") { backStackEntry ->
                    val note = backStackEntry.arguments?.getString("note") ?: ""
                    MainApp(navController, note)
                }
                composable("schedule") {
                    AppSetSchedule(navController)
                }
            }
        }
    }
}



@Composable
@Preview
fun MainApp(navController: NavHostController, note: String) {
    Column(modifier = Modifier.padding(start = 24.dp, top = 56.dp, end = 24.dp)){
        Row (){
            Column() {
                Text(
                    "Good Morning, ",
                    fontSize = 24.sp,
                    color = Color(0xFF1E293B)
                )
                Text(
                    "Shuri",
                    fontSize = 24.sp,
                    color = Color(0xFF1E293B),
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image( modifier = Modifier.size(50.dp),
                painter = painterResource(Res.drawable.icon),
                contentDescription = "icon"
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 29.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CurrentDates()
        }
        Column(modifier = Modifier.padding(top = 8.dp)){
            Text("Schedule Today", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
        Column (modifier = Modifier.padding(top = 32.dp)) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.align(Alignment.TopStart)) {
                    Text("8.00", fontSize = 12.sp, color = Color(0xFF94A3B8))
                    Spacer(modifier = Modifier.height(28.dp))
                    Text("10.00", fontSize = 12.sp, color = Color(0xFF94A3B8))
                }

                Box(
                    modifier = Modifier
                        .padding(start = 85.dp)
                        .fillMaxWidth()
                        .height(76.dp)
                        .background(color = Color(0xFFDE496E),
                            shape = RoundedCornerShape(14.dp))
                        .align(Alignment.TopEnd)
                ){
                    Column(modifier = Modifier.padding(start = 18.dp, top = 14.dp)) {
                        Text(
                            "Rapat dengan Bruce Wayne",
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFFFFFFF)
                        )
                    }
                    Column(modifier = Modifier.align(Alignment.CenterEnd).padding(end = 12.dp, top = 32.dp)){
                        Image(
                            painter = painterResource(Res.drawable.double_icon_1),
                            contentDescription = "double_icon"
                        )
                    }
                }
            }
        }
        Column (modifier = Modifier.padding(top = 32.dp)) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.align(Alignment.TopStart)) {
                    Text("12.00", fontSize = 12.sp, color = Color(0xFF94A3B8))
                    Spacer(modifier = Modifier.height(20.dp))
                    Text("14.00", fontSize = 12.sp, color = Color(0xFF94A3B8))
                    Spacer(modifier = Modifier.height(20.dp))
                    Text("18.00", fontSize = 12.sp, color = Color(0xFF94A3B8))
                }

                Box(
                    modifier = Modifier
                        .padding(start = 85.dp)
                        .fillMaxWidth()
                        .height(112.dp)
                        .background(color = Color(0xFFDE496E), shape = RoundedCornerShape(14.dp))
                        .align(Alignment.TopEnd)
                ){
                    Column(modifier = Modifier.padding(start = 18.dp, top = 24.dp)) {
                        Text(
                            "$note",
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFFFFFFF)
                        )
                    }
                    Column(modifier = Modifier.align(Alignment.CenterEnd).padding(end = 12.dp, top = 65.dp)){
                        Image(
                            painter = painterResource(Res.drawable.double_icon_1),
                            contentDescription = "double_icon"
                        )
                    }
                }
            }
        }
        Column(modifier = Modifier.padding(top = 36.dp)){
            Column(){
                Text("Reminder", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF1E293B))
                Text("Dont forget schedule for tomorrow", fontSize = 12.sp, fontWeight = FontWeight.Normal, color = Color(0xFF575A61))
            }
            Column(modifier = Modifier.padding(top = 14.dp)){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .background(
                            color = Color(0xFF8572FF),
                            shape = RoundedCornerShape(16.dp)
                        )
                ) {
                    Box(modifier = Modifier
                        .padding(top = 8.dp, start = 10.dp, bottom = 8.dp)
                        .size(48.dp)
                        .background(
                            color = Color(0xFFBAB0F9),
                            shape = RoundedCornerShape(10.dp)
                        ),
                        contentAlignment = Alignment.Center
                    ){
                        Image( modifier = Modifier.size(24.dp),
                            painter = painterResource(Res.drawable.icon_calendar),
                            contentDescription = "icon"
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, start = 84.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Urus SIM di samsat Klayatan",
                            fontSize = 12.sp,
                            lineHeight = 26.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                        )
                    }

                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 84.dp, top = 30.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(Res.drawable.icon_time),
                            contentDescription = "clock icon"
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            "12.00 - 16.00",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFFFFFFFF)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(14.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .background(
                            color = Color(0xFF8572FF),
                            shape = RoundedCornerShape(16.dp)
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp, start = 10.dp, bottom = 8.dp)
                            .size(48.dp)
                            .background(
                                color = Color(0xFFBAB0F9),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .align(Alignment.CenterStart),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(Res.drawable.icon_calendar),
                            contentDescription = "calendar icon"
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, start = 84.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Urus SIM di samsat Klayatan",
                            fontSize = 12.sp,
                            lineHeight = 26.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                        )
                    }

                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 84.dp, top = 30.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(Res.drawable.icon_time),
                            contentDescription = "clock icon"
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            "12.00 - 16.00",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFFFFFFFF)
                        )
                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {navController.navigate(Routes.Schedule.route)},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            modifier = Modifier
                .padding(start = 66.dp, end = 66.dp)
                .fillMaxWidth()
                .height(56.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFDE496E),
                            Color(0xFFFF6E91),
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(Float.POSITIVE_INFINITY, 0f)
                    ),
                    shape = RoundedCornerShape(29.dp)
                )
        ) {
            Text(
                "Set schedule",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFFFFFFFF)
            )
        }
    }
}

@Composable
fun CurrentDates() {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())

    val dates = listOf(
        today.minus(3, DateTimeUnit.DAY),
        today.minus(2, DateTimeUnit.DAY),
        today.minus(1, DateTimeUnit.DAY),
        today,
        today.plus(1, DateTimeUnit.DAY),
        today.plus(2, DateTimeUnit.DAY),
        today.plus(3, DateTimeUnit.DAY),
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        dates.forEachIndexed { index, date ->
            when {
                date == today -> {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 13.dp)
                            .offset(y = -10.dp)
                            .width(53.dp)
                            .height(79.dp)
                            .background(
                                color = Color(0xFFFFF0F0),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                date.dayOfMonth.toString(),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFFDE496E)
                            )
                            Text(
                                getShortWeekdayName(date.dayOfWeek),
                                fontSize = 14.sp,
                                color = Color(0xFFDE496E)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .background(
                                        color = Color(0xFFDE496E),
                                        shape = CircleShape)
                            )
                        }
                    }
                }
                index == 2 -> {
                    Column(
                        modifier = Modifier.padding(end = 0.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = date.dayOfMonth.toString(),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = getShortWeekdayName(date.dayOfWeek),
                            fontSize = 12.sp,
                            color = Color(0xFF94A3B8)
                        )
                    }
                }
                index == dates.lastIndex -> {
                    Column(
                        modifier = Modifier.padding(end = 0.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = date.dayOfMonth.toString(),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = getShortWeekdayName(date.dayOfWeek),
                            fontSize = 12.sp,
                            color = Color(0xFF94A3B8)
                        )
                    }
                }
                else -> {
                    Column(
                        modifier = Modifier.padding(end = 38.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = date.dayOfMonth.toString(),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = getShortWeekdayName(date.dayOfWeek),
                            fontSize = 12.sp,
                            color = Color(0xFF94A3B8)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CurrentDatesSchedule() {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val dates = listOf(
        today.minus(1, DateTimeUnit.DAY),
        today,
        today.plus(1, DateTimeUnit.DAY),
        today.plus(2, DateTimeUnit.DAY)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        dates.forEachIndexed { index, date ->
            if (index < 3) {
                if (date == today) {
                    Box(
                        modifier = Modifier
                            .width(73.dp)
                            .height(120.dp)
                            .background(
                                color = Color(0xFF8572FF),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                date.dayOfMonth.toString(),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFFFFFFFF)
                            )
                            Text(
                                getShortWeekdayName(date.dayOfWeek),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFFFFFFFF)
                            )
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .width(73.dp)
                            .height(120.dp)
                            .background(
                                color = Color(0xFFF1F5F9),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                date.dayOfMonth.toString(),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF4A4A4A)
                            )
                            Text(
                                getShortWeekdayName(date.dayOfWeek),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF4A4A4A)
                            )
                        }
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .width(73.dp)
                        .height(120.dp)
                        .background(
                            color = Color(0xFFF1F5F9),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "Other",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF4A4A4A)
                        )
                        Text(
                            "Date",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF4A4A4A)
                        )
                    }
                }
            }

            if (index < dates.lastIndex) {
                Spacer(modifier = Modifier.width(12.dp))
            }
        }
    }
}


fun getShortWeekdayName(dayOfWeek: DayOfWeek): String {
    return when (dayOfWeek) {
        DayOfWeek.MONDAY -> "Mo"
        DayOfWeek.TUESDAY -> "Tu"
        DayOfWeek.WEDNESDAY -> "We"
        DayOfWeek.THURSDAY -> "Th"
        DayOfWeek.FRIDAY -> "Fr"
        DayOfWeek.SATURDAY -> "Sa"
        DayOfWeek.SUNDAY -> "Su"
        else -> throw IllegalArgumentException("Unknown day: $dayOfWeek")
    }
}


@Composable
@Preview
fun AppSetSchedule(navController: NavHostController) {
    Column(modifier = Modifier
        .padding(start = 24.dp)
    )
    {
        Row() {
            Column(modifier = Modifier
                .padding(top = 56.dp)) {
                Text(
                    "Let's set the \nschedule easily",
                    fontSize = 24.sp,
                    color = Color(0xFF1E293B),
                    fontWeight = FontWeight.SemiBold
                )
            }
           Column() {
                Image(
                    painter = painterResource(Res.drawable.bg_image),
                    contentDescription = "Задний фон",
                    modifier = Modifier
                        .height(132.dp)
                        .fillMaxWidth()
                )
            }
        }
        Column(modifier = Modifier.padding(end = 24.dp)){
            Row(){
                Text("Select the date", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
            Spacer(modifier = Modifier.height(16.dp))
            CurrentDatesSchedule()
        }
        Spacer(modifier = Modifier.height(36.dp))
        Row(){
            Text("Select time", fontWeight = FontWeight.Medium, fontSize = 16.sp)
        }
        Row (modifier = Modifier.padding(end = 23.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(88.dp)
                    .background(
                        color = Color(0xFFF1F5F9),
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 33.dp)
                ) {
                    Text("From", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color(0xFF94A3B8))
                    Text("12.00", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                }

               Image(
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .align(Alignment.Center),
                    painter = painterResource(Res.drawable.rightArrow),
                    contentDescription = "Стрелка вправо"
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 33.dp)
                ) {
                    Text("To", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color(0xFF94A3B8))
                    Text("14.00", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                }
            }
        }
        Column (modifier = Modifier.padding(end = 23.dp)){
            Row(modifier = Modifier.padding(top = 34.dp)){
                Text("Category", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
            Row(modifier = Modifier.padding(top = 16.dp)){
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(26.dp)
                        .background(
                            color = Color(0xFFFFFBEB),
                            shape = RoundedCornerShape(16.dp)
                        )
                ){
                    Box(
                        modifier = Modifier
                            .padding(start = 7.dp)
                            .align(Alignment.CenterStart)
                            .size(13.dp)
                            .background(
                                color = Color(0xFFF59E0B),
                                shape = CircleShape
                            )
                    )
                    Text("Meeting", modifier = Modifier.align(Alignment.Center).padding(start = 12.dp), fontSize = 10.sp, fontWeight = FontWeight.Normal)
                }
                Spacer(modifier = Modifier.width(14.dp))
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(26.dp)
                        .background(
                            color = Color(0xFFFDF4FF),
                            shape = RoundedCornerShape(16.dp)
                        )
                ){
                    Box(
                        modifier = Modifier
                            .padding(start = 7.dp)
                            .align(Alignment.CenterStart)
                            .size(13.dp)
                            .background(
                                color = Color(0xFF701A75),
                                shape = CircleShape
                            )
                    )
                    Text("Hangout", modifier = Modifier.align(Alignment.Center).padding(start = 12.dp), fontSize = 10.sp, fontWeight = FontWeight.Normal)
                }
                Spacer(modifier = Modifier.width(14.dp))
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(26.dp)
                        .background(
                            color = Color(0xFFFEF2F2),
                            shape = RoundedCornerShape(16.dp)
                        )
                ){
                    Box(
                        modifier = Modifier
                            .padding(start = 7.dp)
                            .align(Alignment.CenterStart)
                            .size(13.dp)
                            .background(
                                color = Color(0xFFDC2626),
                                shape = CircleShape
                            )
                    )
                    Text("Cooking", modifier = Modifier.align(Alignment.Center).padding(start = 12.dp), fontSize = 10.sp, fontWeight = FontWeight.Normal)
                }
            }
            Row(modifier = Modifier.padding(top = 8.dp)){
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(26.dp)
                        .background(
                            color = Color(0xFFF6F6F6),
                            shape = RoundedCornerShape(16.dp)
                        )
                ){
                    Box(
                        modifier = Modifier
                            .padding(start = 7.dp)
                            .align(Alignment.CenterStart)
                            .size(13.dp)
                            .background(
                                color = Color(0xFF4A4A4A),
                                shape = CircleShape
                            )
                    )
                    Text("Other", modifier = Modifier.align(Alignment.Center).padding(start = 12.dp), fontSize = 10.sp, fontWeight = FontWeight.Normal)
                }
                Spacer(modifier = Modifier.width(14.dp))
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(26.dp)
                        .background(
                            color = Color(0xFFF0FFF5),
                            shape = RoundedCornerShape(16.dp)
                        )
                ){
                    Box(
                        modifier = Modifier
                            .padding(start = 7.dp)
                            .align(Alignment.CenterStart)
                            .size(13.dp)
                            .background(
                                color = Color(0xFF1A7529),
                                shape = CircleShape
                            )
                    )
                    Text("Weekend", modifier = Modifier.align(Alignment.Center).padding(start = 12.dp), fontSize = 10.sp, fontWeight = FontWeight.Normal)
                }
                Image(modifier = Modifier.padding(start = 14.dp).size(24.dp),
                    painter = painterResource(Res.drawable.plusCircle2),
                    contentDescription = "Задний фон"
                )
            }
            var noteText by rememberSaveable { mutableStateOf("") }
            Row(modifier = Modifier.padding(top = 35.dp)){
                Text("Note", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = noteText,
                onValueChange = { noteText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(88.dp)
                    .background(color = Color(0xFFF1F5F9), shape = RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor= Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            val context = LocalContext.current
            Button(
                onClick = {
                    notify(context, "Schedule saved: $noteText")
                    navController.navigate("home/${noteText}")},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier
                    .padding(start = 24.dp, end = 20.dp)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFFF6E91),
                                Color(0xFFDE496E)
                            ),
                            start = Offset(0f, 0f),
                            end = Offset(Float.POSITIVE_INFINITY, 0f)
                        ),
                        shape = RoundedCornerShape(29.dp)
                    )
            ) {
                Text(
                    "Save",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFFFFFFF)
                )
            }
        }
    }
}



