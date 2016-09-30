package pl.dagguh.tournaments.basic.channel

import pl.dagguh.tournaments.spi.ChannelViewDto
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class ChannelEntity(
        @Id
        @GeneratedValue
        var id: Long? = null,
        var type: String = ""
) {
    fun toViewDto(): ChannelViewDto {
        return ChannelViewDto(id = id!!, type = type)
    }
}