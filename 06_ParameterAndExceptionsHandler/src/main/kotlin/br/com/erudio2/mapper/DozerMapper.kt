package br.com.erudio2.mapper

import com.github.dozermapper.core.DozerBeanMapperBuilder
import com.github.dozermapper.core.Mapper

object DozerMapper {

    //    Retornando um mapper
    private val mapper: Mapper = DozerBeanMapperBuilder.buildDefault()

    fun <O, D> parseObject(origin: O, destination: Class<D>?): D {
        return mapper.map(origin, destination)
    }

    //    Retornando uma lista de mapper
    fun <O, D> parseObject(origin: List<O>, destination: Class<D>?): List<D> {

        val destinationObjects: ArrayList<D> = ArrayList()

        for (O in origin) {
            destinationObjects.add(mapper.map(o, destination))
        }
        return destinationObjects

    }

}