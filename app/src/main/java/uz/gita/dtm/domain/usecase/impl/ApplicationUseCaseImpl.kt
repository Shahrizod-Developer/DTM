package uz.gita.dtm.domain.usecase.impl

import uz.gita.dtm.domain.repository.application.ApplicationRepository
import uz.gita.dtm.domain.usecase.ApplicationUseCase

class ApplicationUseCaseImpl constructor(
    private val applicationRepository: ApplicationRepository
): ApplicationUseCase