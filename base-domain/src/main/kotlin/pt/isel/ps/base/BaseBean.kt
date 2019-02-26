package pt.isel.ps.base

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

@Component
abstract class BaseBean(
        val mapperFactory: ModelMapper
) {
    protected fun <F, D> convert(from: F, toClass: Class<D>): D {
        return mapperFactory.map(from, toClass)
    }

    protected fun <F, D> convertList(from: List<F>, toClass: Class<D>): List<D> {
        return convertCollection(from.asSequence(), toClass).toList()
    }

    protected fun <F, D> convertCollection(from: Sequence<F>, toClass: Class<D>): Sequence<D> {
        return from.map { convert(it, toClass) }
    }

    protected fun <F, D> convertSet(from: Set<F>, toClass: Class<D>): Set<D> {
        return convertCollection(from.asSequence(), toClass).toSet()
    }
}