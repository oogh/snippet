package me.oogh.demo.snippet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.oogh.demo.R;
import me.oogh.demo.data.SnippetRepository;
import me.oogh.demo.data.local.SnippetLocalSource;
import me.oogh.demo.utils.ActivityUtils;

public class SnippetActivity extends AppCompatActivity {

    @BindView(R.id.tb_main)
    Toolbar mToolbar;

    @BindString(R.string.app_name)
    String mTitle;

    private SnippetPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        setupToolbar();

        // 创建View -> SnippetFragment
        SnippetFragment fragment = (SnippetFragment) getSupportFragmentManager().findFragmentById(R.id.fl_container);
        if (fragment == null) {
            fragment = SnippetFragment.newInstance();
            ActivityUtils.addFragmentToActivity(R.id.fl_container, getSupportFragmentManager(), fragment);
        }

        // 创建Presenter -> SnippetPresenter
        mPresenter = new SnippetPresenter(fragment, new SnippetRepository(new SnippetLocalSource(this)));
    }

    /**
     * 初始化Toolbar
     */
    private void setupToolbar() {
        mToolbar.setTitle(mTitle);
        setSupportActionBar(mToolbar);
    }
}
