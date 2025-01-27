package com.ainsln.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ainsln.core.designsystem.R

val SfProDisplay = FontFamily(
    Font(R.font.sf_pro_display_regular),
    Font(R.font.sf_pro_display_medium, weight = FontWeight.Medium),
    Font(R.font.sf_pro_display_semibold, weight = FontWeight.SemiBold)
)

val Typography = Typography(
    //For TopBarTitle
    headlineMedium = TextStyle(
        fontFamily = SfProDisplay,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp
    ),
    //Title 1
    titleLarge = TextStyle(
        fontFamily = SfProDisplay,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),
    //Title 2
    titleMedium = TextStyle(
        fontFamily = SfProDisplay,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    //Title 3
    titleSmall = TextStyle(
        fontFamily = SfProDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    //Text 1
    bodyLarge = TextStyle(
        fontFamily = SfProDisplay,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    //Tab text
    labelMedium = TextStyle(
        fontFamily = SfProDisplay,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
    //Number
    labelSmall = TextStyle(
        fontFamily = SfProDisplay,
        fontWeight = FontWeight.Normal,
        fontSize = 7.sp
    ),
)
 object CustomTextStyle {
     //Title 4
     val titleExtraSmall = TextStyle(
         fontFamily = SfProDisplay,
         fontWeight = FontWeight.Medium,
         fontSize = 14.sp
     )
    //Button Text 1
     val buttonText1 = TextStyle(
         fontFamily = SfProDisplay,
         fontWeight = FontWeight.SemiBold,
         fontSize = 16.sp
     )
     //Button Text 2
     val buttonText2 = TextStyle(
         fontFamily = SfProDisplay,
         fontWeight = FontWeight.Normal,
         fontSize = 14.sp
     )
 }
