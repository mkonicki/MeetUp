package konicki.mateusz.greendaosample.match;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import konicki.mateusz.greendaosample.BR;
import konicki.mateusz.greendaosample.R;
import konicki.mateusz.greendaosample.entites.Match;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MatchActivity extends AppCompatActivity {

    MatchObservable match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        match = new MatchObservable(new Match(), this);
        initView(R.layout.activity_match);
    }

    private void initView(int layoutId) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), layoutId, null, false);
        binding.setVariable(BR.match, match);
        setContentView(binding.getRoot());
    }
}
