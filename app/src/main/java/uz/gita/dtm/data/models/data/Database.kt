package uz.gita.dtm.data.models.data

import uz.gita.dtm.data.models.tests.Level
import uz.gita.dtm.data.models.tests.Question


class Database private constructor() {
    private var questions: ArrayList<Question>? = null

    fun initQuestions(): ArrayList<Question>? {
        questions = ArrayList<Question>()
        questions!!.add(
            Question(
                "(425-252+133)-(48+33+225) = ?",
                "600",
                "590",
                "610",
                "620",
                Level.EASY,
                "600"
            )
        )
        questions!!.add(Question("(2+2(2-2×0+2))÷2", "0", "3", "5", "6", Level.EASY, "5"))
        questions!!.add(
            Question(
                "Tenglamani javobini toping?\n" +
                        "|-x|+2=12", "-10;92", "-14;-10", "-10;14", "-10;10", Level.EASY, "-10;10"
            )
        )
        questions!!.add(
            Question(
                "Agar X+Y=11, X-Y=8 bo'lsa X²-Y² toping?",
                "88",
                "80",
                "84",
                "64",
                Level.EASY,
                "88"
            )
        )
        questions!!.add(
            Question(
                "Eng ko'p bo'luvchisi bor sonni toping?",
                "1",
                "0",
                "1N N=100 ta nol",
                "0.M1 M = 100 ta nol",
                Level.EASY,
                "0"
            )
        )
        questions!!.add(
            Question(
                "8π−256+362=?",
                "113.13",
                "131.13",
                "113.31",
                "113",
                Level.EASY,
                "131.13"
            )
        )
        questions!!.add(
            Question(
                "Hisoblang: \uD83E\uDDD0\uD83E\uDDD0\uD83E\uDDD0\uD83E\uDDD0 \n" +
                        "9999−111+1111−11+990−11+222+411−555+55 \uD83D\uDE09\uD83D\uDE09\uD83D\uDE43",
                "110²",
                "100²",
                "11²",
                "11² + 100",
                Level.EASY,
                "110²"
            )
        )
        questions!!.add(
            Question(
                "Xaritada ikki shahar orasidagi masofa3,5 sm gateng. Xaritadagi mashtab 1:4000000 bo`lsa, shaharlar orasidagi haqiqiy masofa necha km.",
                "140 km", "80 km", "260 km", "100 km", Level.EASY, "140 km"
            )
        )
        questions!!.add(Question("(2+2)!=", "6", "18", "24", "20", Level.EASY, "24"))
        questions!!.add(
            Question(
                "4/7+8/14+6/7=?",
                "9/7",
                "14/7",
                "4/7",
                "10/7",
                Level.EASY,
                "14/7"
            )
        )
        questions!!.add(
            Question(
                "√45+√25−0,25=?",
                "10.01",
                "11.45",
                "12.56",
                "10",
                Level.EASY,
                "11.45"
            )
        )
        questions!!.add(
            Question(
                "773+111+257+89+201",
                "1222",
                "1431",
                "1453",
                "1413",
                Level.EASY,
                "1431"
            )
        )
        questions!!.add(Question("31²-960+10²=?", "100", "130", "101", "105", Level.EASY, "101"))
        questions!!.add(
            Question(
                "392 ni qanday songa bo‘lganda bo‘linma 17 va \n" +
                        "qoldiq 1 bo‘ladi?", "19", "21", "22", "23", Level.EASY, "23"
            )
        )
        questions!!.add(Question(" EKUB ( 24;90 ) ni toping?", "6", "2", "4", "3", Level.EASY, "6"))
        questions!!.add(
            Question(
                "2 nafar ishchi 2 soatda  2 ta o'yinchoq yasaydi. 4 nafar ishchi 4 soatda nechta o'yinchoq yasaydi?",
                "4",
                "8",
                "16",
                "32",
                Level.EASY,
                "8"
            )
        )
        questions!!.add(
            Question(
                "427ta 427ning yigʻindisini 9ga boʻlgandagi qoldiqni toping",
                "5",
                "3",
                "2",
                "7",
                Level.EASY,
                "7"
            )
        )
        questions!!.add(
            Question(
                " Musbat to'g'ri kasrning surati 20% ga oshirilib, maxraji 20% ga kamaytirilsa, kasrning qiymati qanday o'zgaradi?",
                "40 % ga ortadi",
                "50 % ga ortadi",
                "50 % ga kamayadi",
                "40 % ga kamayadi",
                Level.EASY,
                "50 % ga ortadi"
            )
        )
        questions!!.add(
            Question(
                "4 ta ishchi ma'lum ishni 30 soatda bajaradi. Shu ishni nechta ishchi 12 soatda bajaradi?",
                "9",
                "10",
                "11",
                "12",
                Level.EASY,
                "10"
            )
        )
        questions!!.add(
            Question(
                "21, 9, 21, 11, 21, 13, 21,.... ?\n" +
                        "Keyingi raqamni toping !", "22", "21", "15", "18", Level.EASY, "15"
            )
        )
        questions!!.add(
            Question(
                "510000000000000000 tub bo'luvchilari soni nechta?  \uD83E\uDDD0\uD83E\uDDD0\uD83E\uDDD0",
                "126",
                "4",
                "0",
                "97",
                Level.HARD,
                "4"
            )
        )
        questions!!.add(
            Question(
                "-1/4;  -1/5, ......\n" +
                        "arifmetik progressiyaning nechta hadi manfiy?",
                "10",
                "7",
                "6",
                "T.J.Y",
                Level.HARD,
                "T.J.Y"
            )
        )
        questions!!.add(
            Question(
                "Agar barcha x, y lar uchun\n" +
                        "x³+5x²y-axy²+3xy-bx²y+6xy²+dxy+y³=x³+y³ ayniyat bo'lsa,  a ni toping?",
                "6",
                "-6",
                "-7",
                "7",
                Level.HARD,
                "6"
            )
        )
        questions!!.add(
            Question(
                "64, 32,  16,  geometrik progessiyaning to'qqizinchi hadi oltinchi hadidan nechtaga kam?\uD83E\uDD13",
                "1.125",
                "1.25",
                "0.75",
                "1.75",
                Level.HARD,
                "1.75"
            )
        )
        questions!!.add(
            Question(
                "k ning  qanday qiymatida \n" +
                        " 3x+(k-1)y=k+1\n" +
                        "(k+1)x+y=3 tenglamalar sistemasi cheksiz ko'p yechimga ega bo'ladi?",
                "1",
                "2",
                "0",
                "-1.75",
                Level.HARD,
                "2"
            )
        )
        questions!!.add(
            Question(
                "(96-7-1)Ifodani uning hadlarini guruppalab umumiy kopaytuvchini qavsdan tashqariga chiqarish yordamida hisoblang",
                "89", "92", "97", "100", Level.HARD, "100"
            )
        )
        questions!!.add(
            Question(
                "4! * 3!×4  *urniga mos belgini qòying",
                "<",
                ">",
                "=",
                "T.J.Y",
                Level.HARD,
                "="
            )
        )
        questions!!.add(
            Question(
                "\uD83D\uDCA5Men bir son o’yladim. Uning yarmiga choragini qo’shsak 18 hosil bo’ladi Savol men qanday son o’yladim❓",
                "39", "24", "18", "42", Level.HARD, "24"
            )
        )
        questions!!.add(
            Question(
                "Hadlari a=2n+3 qonuniyat bilan aniqlanadigan ketma - ketlikning 12 hadini toping ?",
                "24", "27", "30", "32", Level.HARD, "27"
            )
        )
        questions!!.add(
            Question(
                "3*3*3*3=10.  * òrniga mmatematik amalni qòyib 10 chiqaring",
                "/ * +", "/ + *", "+ / *", "- / +", Level.HARD, "/ + *"
            )
        )
        questions!!.add(
            Question(
                "3•6•9•...•297•300 ko'paytma nechta 0 bilan tugaydi?",
                "20", "18", "24", "12", Level.HARD, "24"
            )
        )
        questions!!.add(
            Question(
                "'malum qonun' degan ma'noni anglatuvchi termini aniqlang.",
                "Algoritim", "Formula", "Funksiya", "Teorema", Level.HARD, "Formula"
            )
        )
        questions!!.add(
            Question(
                "(((((((9)¹)³)²)⁰)⁵)¹)×100=?",
                "1294992", "100", "1", "10", Level.HARD, "100"
            )
        )
        questions!!.add(
            Question(
                ("Qiziqarli matematika\n" +
                        "\n" +
                        "Butun sonlarining kvadrati qaysi raqamlar bilan tugamaydi?"),
                "6, 7, 8",
                "0, 3, 8",
                "2, 3, 7",
                "T.J.Y",
                Level.HARD,
                "2, 3, 7"
            )
        )
        questions!!.add(
            Question(
                "Quyidagilardan qaysi biri y=(2/x-1)-1 funktsiyaga teskari funktsiya?",
                "y = 1 - 2/(x + 1)",
                "y = 2/(x + 1) + 1",
                "y = 2 -(3/x)",
                "y = -2/(x + 1)",
                Level.HARD,
                "y = 2/(x + 1) + 1"
            )
        )
        questions!!.add(
            Question(
                "Ushbu funktsiya  y=x²+3x-5 funksiya  (1; -1)  nuqtadan o'tsa bunga teskari bo'lgan funktsiya qaysi nuqtadan o'tadi?",
                "(2;-1)",
                "(1;-1)",
                "(-1;1)",
                "(3;1)",
                Level.HARD,
                "(-1;1)"
            )
        )
        questions!!.add(
            Question(
                "Bolalar bog’chasida 40 ta o’gil bola va 28 ta qiz bola bir-birlarini qo’lini ushlab katta aylana hosil qilishdi. 18 ta bola o’ng qo’li bilan qizlarni qo’lini tutib turibdi. Nechta bola chap qo’li bilan qizlarni qo’lini tutib turibdi?",
                "18",
                "9",
                "28",
                "14",
                Level.HARD,
                "18"
            )
        )
        questions!!.add(
            Question(
                "Sotuvchi ikki mahsulotini $60 dan sotdi. U birinchi masulotdan 20% foyda, ikkinchisida 20% zarar ko’rdi. Keyin umuman hisobda, u …",
                "10 % foyda qildi",
                "5 % foyda qildi",
                "5 % zarar qildi",
                "Foyda ham zarar ham ko'rmadi",
                Level.HARD,
                "5 % zarar qildi"
            )
        )
        questions!!.add(
            Question(
                "4 yoshli bola kuniga o'rtacha nechta savol\uD83E\uDD14so'raydi⁉️",
                "50 ta",
                "200 ta",
                "450 ta",
                "1000 ta",
                Level.HARD,
                "450 ta"
            )
        )
        questions!!.add(
            Question(
                "Farhod 1 mb internet trafik uchun 10 so'm sarflaydi va kuniga 12 mb trafik ishlatadi. Farhod N haftada qancha pul sarflaydi?",
                "120N",
                "560N",
                "650N",
                "840N",
                Level.HARD,
                "840N"
            )
        )
        questions!!.add(
            Question(
                "Ushbu |x-6|<4  tengsizlikni yeching?",
                "-2 < x < 10",
                "2 < x < 10",
                "-10 < x < 2",
                "T.J.Y",
                Level.MEDIUM,
                "2 < x < 10"
            )
        )
        questions!!.add(
            Question(
                "30° li 3 litr suv va 4 litr 40° li suvlar aralashtirikgandagi haroratni toping",
                "30",
                "40",
                "20",
                "36",
                Level.MEDIUM,
                "30"
            )
        )
        questions!!.add(
            Question(
                "25!-24! Sonining butun boʻluvchilari yigʻindisini toping",
                "12000",
                "120",
                "1245",
                "0",
                Level.MEDIUM,
                "0"
            )
        )
        questions!!.add(
            Question(
                "1+7+13+19+...+X=280  \n" +
                        "Tenglamani yeching.", "35", "45", "55", "63", Level.MEDIUM, "35"
            )
        )
        questions!!.add(
            Question(
                "3 ta tub sonni yig'indisi 6 ga teng\n" +
                        "Agar 2-son 3 bo'lsa qolganlarini toping",
                "0 va 3",
                "1 va 2",
                "Bo'lishi mumkin emas",
                "2 va 3",
                Level.MEDIUM,
                "Bo'lishi mumkin emas"
            )
        )
        questions!!.add(
            Question(
                "Surat va maxraji 25 dan katta boʻlmagan turli tub sonlardan iborat boʻlgan nechta oddiy kasr mavjud?",
                "72",
                "62",
                "84",
                "30",
                Level.MEDIUM,
                "72"
            )
        )
        questions!!.add(
            Question(
                "3,4,5,6 sonlarini takrorlamasdan nechta 4 xonali son tuzish mumkin?",
                "256",
                "14",
                "24",
                "196",
                Level.MEDIUM,
                "24"
            )
        )
        questions!!.add(
            Question(
                "Aziz 1 daqiqada 180 ta so'z o'qiydi. Nigora undan 5/6 marta kam o'qiydi. Nodira ularni ikkalasi o'qiganini qo'shgandan , 170 ga kam o'qiydi. Nodira 1 daqiqada qancha so'z o'qiydi",
                "150",
                "160",
                "170",
                "180",
                Level.MEDIUM,
                "160"
            )
        )
        questions!!.add(
            Question(
                "Quyidagi ketma-ketlikdagi keyingi sonni toping :\n" + "12 → 22 → 61 → 42 → ?",
                "50",
                "78",
                "25",
                "33",
                Level.MEDIUM,
                "25"
            )
        )
        questions!!.add(
            Question(
                " Oxirgi raqamini toping :  2¹⁰⁰⁰",
                "2",
                "4",
                "6",
                "8",
                Level.MEDIUM,
                "6"
            )
        )
        questions!!.add(
            Question(
                "2021-yil dastlabki uch oy smetasi bo'yicha Toshkent shahri Prezident maktabiga budjet mablag’laridan 10730 mln so’m mablag’ ajratilgan. Agar maktabda 6 ta sinfda 24 tadan o’quvchi o’qisa, davlat bitta o’quvchi uchun oyiga o’rtacha qancha mablag’ ajratgan?",
                "2 500 000 so'm",
                "12 500 000 so'm",
                "25 000 000 so'm",
                "49 000 000 so'm",
                Level.MEDIUM,
                "25 000 000 so'm"
            )
        )
        questions!!.add(
            Question(
                "Ikki sonning ayirmasi kamayuvchidan 13 taga kam va ayriluvchidan 5 taga ortiq. Kamayuvchini toping.",
                "18",
                "26",
                "31",
                "36",
                Level.MEDIUM,
                "31"
            )
        )
        questions!!.add(
            Question(
                (" Mantiqiy Savol\n" +
                        "\n" +
                        "3, 4, 2, 4, 3, 4, ... Keyingi sonni toping."),
                "2",
                "3",
                "4",
                "5",
                Level.MEDIUM,
                "6"
            )
        )
        questions!!.add(
            Question(
                "x‚ 24 va 30 sonlarining EKUBI 6ga‚ EKUKI 360 ga teng bo'lsa‚ x ming kichik qiymatni toping.",
                "10", "12", "18", "24", Level.MEDIUM, "18"
            )
        )
        questions!!.add(
            Question(
                "\uD83C\uDF70Uch Marta kesish orqali bitta tortni eng ko'pi bilan nechta bo'lakka bo'lish mumkin?",
                "6",
                "8",
                "9",
                "10",
                Level.MEDIUM,
                "8"
            )
        )
        questions!!.add(
            Question(
                ("#Qiziqarli_Savol❓\n" +
                        "\n" +
                        "Ahmad bir son o'yladi. Sonning yarimga 3ni qo'shdi. Hosil bo‘lgan sonni  2ga ko'paytirdi. Natijada 14 hosil bo'ldi. Ahmad o'ylagan Sonni toping"),
                "5", "4", "6", "8", Level.MEDIUM, "8"
            )
        )
        questions!!.add(Question("8-3!=?", "2", "4", "3", "1", Level.MEDIUM, "2"))
        questions!!.add(
            Question(
                "-78 vq -66 sonlarining 5ga bo'lgandagi qoldiqlar kopaytamsini toping ?",
                "12", "8", "10", "24", Level.MEDIUM, "8"
            )
        )
        questions!!.add(
            Question(
                "a+b=4  bo'lsa  (a+b)² ni toping?", "4", "8", "16", "64", Level.MEDIUM, "16"
            )
        )
        questions!!.add(
            Question(
                "Agar 48×n  (n€N) ifofa biror natural soning kvadrati bo'lsa, n+13  ning eng kichik qiymatini toping?",
                "13", "16", "15", "17", Level.MEDIUM, "6"
            )
        )
        return questions
    }

    companion object {
        private var repository: Database? = null
        val instance: Database?
            get() {
                if (repository == null) {
                    repository = Database()
                }
                return repository
            }
    }
}
