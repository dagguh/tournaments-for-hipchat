package pl.dagguh.tournaments.hipchat

data class HipchatCommandDto(var item: Item = HipchatCommandDto.Item()) {

    data class Item(var message: Message = Item.Message(), var room: Room = Item.Room()) {

        data class Message(var message: String = "")

        data class Room(var id: Long = 0)
    }
}