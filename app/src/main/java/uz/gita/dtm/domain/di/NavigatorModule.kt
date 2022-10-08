package uz.gita.dtm.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.dtm.presentation.navigation.Handler
import uz.gita.dtm.presentation.navigation.NavigationDispatcher
import uz.gita.dtm.presentation.navigation.Navigator

@Module
@InstallIn(SingletonComponent::class)
interface NavigatorModule {

    @Binds
    fun bindsNavigator(impl: NavigationDispatcher): Navigator

    @Binds
    fun bindsHandler(impl: NavigationDispatcher): Handler
}