package uz.gita.dtm.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.dtm.domain.usecase.NewsUseCase
import uz.gita.dtm.domain.usecase.ServiceUseCase
import uz.gita.dtm.domain.usecase.impl.NewsUseCaseImpl
import uz.gita.dtm.domain.usecase.impl.ServiceUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindsNewsUseCase(impl:NewsUseCaseImpl):NewsUseCase

    @Binds
    fun bindsServiceUseCase(impl: ServiceUseCaseImpl):ServiceUseCase
}