package uz.gita.dtm.data.models.persondata

data class Pay(
    val id: String,
    val invoiceNumber: String,
    val state: Boolean,
    val balance: String
)