package pl.dagguh.tournaments.channel

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