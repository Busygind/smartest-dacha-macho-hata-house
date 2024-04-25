package ru.itmo.smartesthata.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.itmo.smartesthata.R

val NunitoSans = FontFamily(
    Font(R.font.nunito_sans_regular),
    Font(R.font.nunito_sans_light, weight = FontWeight.Light),
    Font(R.font.nunito_sans_semibold, weight = FontWeight.SemiBold),
    Font(R.font.nunito_sans_bold, weight = FontWeight.Bold),
)

val Rounded = FontFamily(
    Font(R.font.rounded_regular),
    Font(R.font.rounded_light, weight = FontWeight.Light),
    Font(R.font.rounded_semibold, weight = FontWeight.SemiBold),
    Font(R.font.rounded_bold, weight = FontWeight.Bold),
)

val CURRENT_FONT = Rounded

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = CURRENT_FONT,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    h2 = TextStyle(
        fontFamily = CURRENT_FONT,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    h5 = TextStyle(
        fontFamily = CURRENT_FONT,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
    ),
    subtitle1 = TextStyle(
        fontFamily = CURRENT_FONT,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    body1 = TextStyle(
        fontFamily = CURRENT_FONT,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),
    body2 = TextStyle(
        fontFamily = CURRENT_FONT,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    button = TextStyle(
        fontFamily = CURRENT_FONT,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
    ),
    caption = TextStyle(
        fontFamily = CURRENT_FONT,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
    ),
)