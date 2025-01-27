package com.ainsln.core.designsystem.icon

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.ainsln.core.designsystem.R
import com.ainsln.core.designsystem.theme.DarkBlue
import com.ainsln.core.designsystem.theme.DarkGreen

object AppIcons {

    val Filter: ImageVector
        @Composable
        get() = ImageVector.vectorResource(R.drawable.ic_filter)

    val Search: ImageVector
        @Composable
        get() = ImageVector.vectorResource(R.drawable.ic_search)

    val FavoriteActive: ImageVector
        @Composable
        get() = ImageVector.vectorResource(R.drawable.ic_heart_active)

    val FavoriteInactive: ImageVector
        @Composable
        get() = ImageVector.vectorResource(R.drawable.ic_heart_inactive)

    val Experience: ImageVector
        @Composable
        get() = ImageVector.vectorResource(R.drawable.ic_experience)

    val Verification: ImageVector
        @Composable
        get() = ImageVector.vectorResource(R.drawable.ic_verification)

    val Back: ImageVector
        @Composable
        get() = ImageVector.vectorResource(R.drawable.ic_back)

    val Sort: ImageVector
        @Composable
        get() = ImageVector.vectorResource(R.drawable.ic_sort)

    val Error: ImageVector
        @Composable
        get() = ImageVector.vectorResource(R.drawable.ic_error)

    val Empty: ImageVector
        @Composable
        get() = ImageVector.vectorResource(R.drawable.ic_empty)

    val StarFavorite: ImageVector
        @Composable
        get() = ImageVector.vectorResource(R.drawable.ic_star_favorite)

    private val Location: ImageVector
        @Composable
        get() = ImageVector.vectorResource(R.drawable.ic_location)

    private val Star: ImageVector
        @Composable
        get() = ImageVector.vectorResource(R.drawable.ic_star)

    private val TemporaryJob: ImageVector
        @Composable
        get() = ImageVector.vectorResource(R.drawable.ic_temporary_job)

    @Composable
    fun getOfferIcon(id: String?): Pair<ImageVector, Color>?{
        return when(id){
            "near_vacancies" -> Pair(Location, DarkBlue)
            "level_up_resume" -> Pair(Star, DarkGreen)
            "temporary_job" -> Pair(TemporaryJob, DarkGreen)
            else -> null
        }
    }

}
