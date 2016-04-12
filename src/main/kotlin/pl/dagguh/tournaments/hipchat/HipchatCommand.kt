package pl.dagguh.tournaments.hipchat

data class HipchatCommand(var item: Item = HipchatCommand.Item()) {

    data class Item(var message: Message = Item.Message(), var room: Room = Item.Room()) {

        data class Message(var message: String = "")

        data class Room(var id: Long = 0)
    }
}