package uz.gita.dtm.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.dtm.domain.repository.applicant.ApplicantRepository
import uz.gita.dtm.domain.repository.applicant.impl.ApplicantRepositoryImpl
import uz.gita.dtm.domain.repository.application.ApplicationRepository
import uz.gita.dtm.domain.repository.auth.AuthRepository
import uz.gita.dtm.domain.repository.auth.impl.AuthRepositoryImpl
import uz.gita.dtm.domain.repository.news.NewsRepository
import uz.gita.dtm.domain.repository.news.impl.NewsRepositoryImpl
import uz.gita.dtm.domain.repository.service.ServiceRepository
import uz.gita.dtm.domain.repository.service.impl.ServiceRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface ApplicantRepositoryModule {

    @Binds
    fun bindsApplicationRepository(impl: ApplicationRepository): ApplicationRepository

    @Binds
    fun bindsApplicantRepository(impl:ApplicantRepositoryImpl):ApplicantRepository

    @Binds
    fun bindsAuthRepository(impl: AuthRepositoryImpl):AuthRepository

    @Binds
    fun bindsNewsRepository(impl:NewsRepositoryImpl):NewsRepository

    @Binds
    fun bindsServiceRepository(impl: ServiceRepositoryImpl):ServiceRepository
}