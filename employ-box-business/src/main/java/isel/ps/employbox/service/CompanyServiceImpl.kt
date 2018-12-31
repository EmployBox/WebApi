package isel.ps.employbox.service

import isel.ps.base.MapperFactoryBean
import isel.ps.employbox.business.AbstractBaseEntityBO
import isel.ps.employbox.business.CompanyBO
import isel.ps.employbox.domain.Company
import isel.ps.employbox.model.CompanyModel
import isel.ps.employbox.model.PageModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author tiago.ribeiro
 */
@Component
class CompanyServiceImpl(
        mapperFactoryBean: MapperFactoryBean,
        override val business: CompanyBO
) : AbstractBaseEntityService<Company, CompanyModel, Long>(mapperFactoryBean), CompanyService {

    override val modelClass: Class<CompanyModel> = CompanyModel::class.java

    override val entityClass: Class<Company> = Company::class.java

    override fun convertBeforeSave(model: CompanyModel) = convert(model, entityClass)

    override fun findByParams(ratingLow: Int, ratingHigh: Int, name: String, specialization: String, yearFounded: Int, address: String, orderColumn: String, orderClause: String, page: Int, pageSize: Int): PageModel<CompanyModel> {
        return convert(business.findByParams(ratingLow, ratingHigh, name, specialization, yearFounded, orderColumn, orderClause, page, pageSize), PageModel::class.java)
                .map { convert(it, modelClass) }
    }
}