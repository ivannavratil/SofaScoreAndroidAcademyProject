package sofascore.pokedex


import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.snackbar.Snackbar
import sofascore.pokedex.ui.viewmodel.FavoriteViewModel


class PopUpClass {
    //PopupWindow display method
    fun showPopupWindow(view: View, favoriteViewModel: FavoriteViewModel) {

        val popupView: View =
            LayoutInflater.from(view.context).inflate(R.layout.settings_clear_favorites_popup, null);

        //Specify the length and width through constants
        val width = LinearLayout.LayoutParams.MATCH_PARENT
        val height = LinearLayout.LayoutParams.MATCH_PARENT

        //Make Inactive Items Outside Of PopupWindow
        val focusable = true

        //Create a window with our parameters
        val popupWindow = PopupWindow(popupView, width, height, focusable)

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)

        val buttonClear: AppCompatButton = popupView.findViewById(R.id.button_clear);
        val buttonCancel: AppCompatButton = popupView.findViewById(R.id.button_cancel);


        buttonCancel.setOnClickListener { popupWindow.dismiss(); }

        buttonClear.setOnClickListener {
            favoriteViewModel.clearFavouritePokemons()
            val snackbar = Snackbar.make(
                view,
                view.context.getString(R.string.favorites_list_is_clear), Snackbar.LENGTH_SHORT
            )
                .setAction("✕") {}
                .setBackgroundTint(view.context.resources.getColor(R.color.dark))
                .setAnimationMode(Snackbar.ANIMATION_MODE_FADE)

            snackbar.show()
            popupWindow.dismiss()
        }


    }
}