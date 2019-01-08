package pt.isel.ps.employbox.model

/**
 * @author tiago.ribeiro
 */
open class PageModel<T>(
        private val content: List<T>,
        private val totalPages: Int,
        private val totalElements: Int,
        private val number: Int
) {
    fun<R> map(mapper: (T) -> (R)) =
            PageModel(
                    this.content.map(mapper),
                    totalPages,
                    totalElements,
                    number
            )
}