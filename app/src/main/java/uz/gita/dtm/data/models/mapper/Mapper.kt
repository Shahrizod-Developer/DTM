package uz.gita.dtm.data.models.mapper

import com.google.firebase.firestore.DocumentSnapshot
import uz.gita.dtm.data.models.news.News
import uz.gita.dtm.data.models.persondata.ApplicantAddress
import uz.gita.dtm.data.models.persondata.Application
import uz.gita.dtm.data.models.persondata.Education
import uz.gita.dtm.data.models.persondata.Passport

object Mapper {

    fun DocumentSnapshot.toPassport() = Passport(
        firstName = this["first_name"].toString(),
        lastName = this["last_name"].toString(),
        fatherName = this["father_name"].toString(),
        jShShir = this["jshshr"].toString().toLong(),
        passportsSeries = this["series"].toString(),
        passportSeriesNumber = this["number"].toString().toLong()
    )

    fun DocumentSnapshot.toAddress() = ApplicantAddress(
        id = this["id"].toString(),
        region = this["region"].toString(),
        district = this["district"].toString(),
        address = this["address"].toString()
    )

    fun DocumentSnapshot.toEducation() = Education(
        id = this["id"].toString(),
        name = this["name"].toString(),
        region = this["region"].toString(),
        district = this["district"].toString(),
        institution = this["institution"].toString(),
        yearOfGraduation = this["year_of_graduation"].toString(),
        documentSeries = this["series"].toString(),
        documentNumber = this["number"].toString().toLong()
    )

    fun DocumentSnapshot.toApplication() = Application(
        id = this["id"].toString(),
        title = this["title"].toString(),
        image = this["image"].toString(),
        number = this["number"].toString(),
        date = this["date"].toString().toLong(),
        time = this["time"].toString().toLong(),
        state = this["state"].toString().toBoolean(),
        pay = this["pay"].toString(),
        applicantName = this["applicant_name"].toString(),
        region = this["region"].toString(),
        selectedDirection = this["selection_direction"].toString(),
        targetEducation = this["target_education"].toString(),
        selectionPriority = this["selection_priority"].toString(),
        language = this["language"].toString()
    )

    fun DocumentSnapshot.toNews() = News(
        id = this["id"].toString(),
        desc = this["desc"].toString(),
        image = this["image"].toString(),
        title = this["title"].toString(),
        date = this["date"].toString().toLong()
    )
}