package pt.isel.ps.base.model

/**
 * @author tiago.ribeiro
 */

open class PageModel<T> @JvmOverloads constructor(
        var content: List<T>? = emptyList(),
        var totalPages: Int? = null,
        var totalElements: Int? = null,
        var number: Int? = null
) {
    fun<R> map(mapper: (T) -> (R)) =
            PageModel(
                    this.content!!.map(mapper),
                    totalPages,
                    totalElements,
                    number
            )
}