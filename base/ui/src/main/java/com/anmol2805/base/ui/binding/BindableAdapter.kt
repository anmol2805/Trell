package com.anmol2805.base.ui.binding

/**
 * Allows DataBinding to set items into [RecyclerView.Adapter]. Refer [RecyclerView.setItems].
 */
interface BindableAdapter<T> {
    var items: List<T>
}