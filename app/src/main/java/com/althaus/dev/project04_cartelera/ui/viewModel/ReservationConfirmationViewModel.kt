package com.althaus.dev.project04_cartelera.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.althaus.dev.project04_cartelera.data.model.Reservation
import com.althaus.dev.project04_cartelera.data.repository.ReservationRepository
import kotlinx.coroutines.launch

class ReservationConfirmationViewModel(private val reservationRepository: ReservationRepository) : ViewModel() {

    private val _reservationConfirmed = MutableLiveData<Boolean>()
    val reservationConfirmed: LiveData<Boolean> get() = _reservationConfirmed

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun confirmReservation(reservation: Reservation) {
        viewModelScope.launch {
            try {
                reservationRepository.makeReservation(reservation)
                _reservationConfirmed.value = true
            } catch (e: Exception) {
                _errorMessage.value = "Error al confirmar la reserva: ${e.message}"
            }
        }
    }
}