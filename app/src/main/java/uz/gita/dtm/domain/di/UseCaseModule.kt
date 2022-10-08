package uz.gita.dtm.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.dtm.domain.usecase.*
import uz.gita.dtm.domain.usecase.impl.*

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindsNewsUseCase(impl: NewsUseCaseImpl): NewsUseCase

    @Binds
    fun bindsServiceUseCase(impl: ServiceUseCaseImpl): ServiceUseCase

    @Binds
    fun bindsProfileUseCase(impl: ProfileUseCaseImpl): ProfileUseCase

    @Binds
    fun bindsHomeUseCase(impl: HomeUseCaseImpl): HomeUseCase

    @Binds
    fun bindsApplicationUseCase(impl:ApplicationUseCaseImpl):ApplicationUseCase
}