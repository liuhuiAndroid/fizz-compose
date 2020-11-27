package com.example.owl.ui

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import com.example.owl.ui.course.CourseDetails
import com.example.owl.ui.courses.Courses
import com.example.owl.ui.onboarding.Onboarding
import com.example.owl.ui.utils.AmbientBackDispatcher
import com.example.owl.ui.utils.Navigator
import com.example.owl.ui.utils.ProvideImageLoader
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@Composable
fun OwlApp(backDispatcher: OnBackPressedDispatcher) {
    @Suppress("RemoveExplicitTypeArguments")
    val navigator: Navigator<Destination> = rememberSavedInstanceState(
        saver = Navigator.saver<Destination>(backDispatcher)
    ) {
        Navigator(Destination.Onboarding, backDispatcher)
    }
    val actions = remember(navigator) { Actions(navigator) }

    Providers(AmbientBackDispatcher provides backDispatcher) {
        ProvideWindowInsets {
            ProvideImageLoader {
                Crossfade(navigator.current) { destination ->
                    when (destination) {
                        Destination.Onboarding -> Onboarding(actions.onboardingComplete)
                        Destination.Courses -> Courses(actions.selectCourse)
                        is Destination.Course -> CourseDetails(
                            destination.courseId,
                            actions.selectCourse,
                            actions.upPress
                        )
                    }
                }
            }
        }
    }
}
