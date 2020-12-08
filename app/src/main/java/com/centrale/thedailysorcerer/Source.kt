package com.centrale.thedailysorcerer

class Source (id: String?, name: String?) {

    private var id: String?
    private var name: String?

    init {
        this.id = id
        this.name = name
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }
}