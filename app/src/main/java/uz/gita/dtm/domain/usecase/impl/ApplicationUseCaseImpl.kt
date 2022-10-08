package uz.gita.dtm.domain.usecase.impl

import uz.gita.dtm.domain.repository.application.ApplicationRepository
import uz.gita.dtm.domain.usecase.ApplicationUseCase
import javax.inject.Inject

class ApplicationUseCaseImpl @Inject constructor(
    private val applicationRepository: ApplicationRepository
): ApplicationUseCase