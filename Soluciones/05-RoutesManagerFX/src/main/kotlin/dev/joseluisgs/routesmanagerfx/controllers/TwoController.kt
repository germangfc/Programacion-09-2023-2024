package dev.joseluisgs.routesmanagerfx.controllers

import dev.joseluisgs.routesmanagerfx.data.parameters.DemoParameterState
import dev.joseluisgs.routesmanagerfx.data.viewmodels.DemoViewModel
import dev.joseluisgs.routesmanagerfx.routes.RoutesManager
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.stage.Stage
import org.lighthousegames.logging.logging

private val logger = logging()

class TwoController {
    @FXML
    private lateinit var botonCambiar: Button

    @FXML
    private lateinit var datosLabel: Label

    @FXML
    private lateinit var textViewModel: TextField

    @FXML
    private fun initialize() {
        logger.debug { "Inicializando la vista Two" }
        // evento del boton
        botonCambiar.setOnAction { onBotonCambiarClick() }

        // Recogemos los datos que nos pasan, es muy cutre y no tiene reactividad!!!!
        // usalo para ver que pasa
        datosLabel.text = DemoParameterState.data

        // Enlazamos el modelo de datos a la vista, Si cambia el modelo cambia la vista y viceversa
        textViewModel.textProperty().bindBidirectional(DemoViewModel.mensaje)
    }

    private fun onBotonCambiarClick() {
        println("Boton Cambiar pulsado cambiando de 2 -> 1")
        // Sobre que Stage?
        // Somo estamos fuera de la ventana principal, no es MainStage
        // Debemos saber que NodoRaiz o parent en el que estamos
        // un truco es usar un elemento que tengamos
        val stage = botonCambiar.scene.window as Stage
        // RoutesManager.changeScene(stage, RoutesManager.Views.TWO)
        // o podemos usar el activeStage
        //RoutesManager.changeScene(RoutesManager.activeStage, RoutesManager.Views.TWO)
        // O el active stage que ya esta definido como parámetro por defecto
        DemoParameterState.data = "Te mando esto desde la escena 2"
        // Podemos tocar aquí el modelo de datos
        DemoViewModel.mensaje.set("Reacciona a Escena 2")
        DemoViewModel.numVeces.value += 1
        RoutesManager.changeScene(view = RoutesManager.View.ONE)
    }
}
