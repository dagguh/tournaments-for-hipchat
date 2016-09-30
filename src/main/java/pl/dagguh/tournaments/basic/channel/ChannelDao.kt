package pl.dagguh.tournaments.basic.channel

import pl.dagguh.tournaments.spi.ChannelViewDto
import java.util.*
import java.util.Optional.ofNullable
import javax.persistence.EntityManager

class ChannelDao(val entityManager: EntityManager) {

    fun create(type: String): ChannelViewDto {
        val channel = ChannelEntity(type = type)
        entityManager.persist(channel)
        return channel.toViewDto()
    }

    fun find(id: Long): Optional<ChannelViewDto> {
        val channel = entityManager.find(ChannelEntity::class.java, id)
        return ofNullable(channel).map { it.toViewDto() }
    }
}

