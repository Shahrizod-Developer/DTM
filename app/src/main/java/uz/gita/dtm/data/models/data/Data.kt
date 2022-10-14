package uz.gita.dtm.data.models.data

import uz.gita.dtm.data.models.persondata.Admission

class Data {

    companion object {


        val admissionList = arrayListOf<Admission>(
            Admission("Shaxsiy ma'lumotlar", 0),
            Admission("Doimiy yashash manzili", 0),
            Admission("Umum ta'lim muassasasi", 0),
            Admission("Sertifikatlar", 0),
            Admission("Imtiyozlar", 0),
            Admission("Yo'nalish tanlang", 0)
        )
    }
}