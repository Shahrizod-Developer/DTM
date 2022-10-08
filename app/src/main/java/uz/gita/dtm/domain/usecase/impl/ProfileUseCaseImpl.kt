package uz.gita.dtm.domain.usecase.impl

import uz.gita.dtm.domain.repository.applicant.ApplicantRepository
import uz.gita.dtm.domain.usecase.ProfileUseCase
import javax.inject.Inject

class ProfileUseCaseImpl @Inject constructor(
    private val applicantRepository: ApplicantRepository
): ProfileUseCase