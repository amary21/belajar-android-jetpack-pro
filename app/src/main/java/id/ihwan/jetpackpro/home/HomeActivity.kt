package id.ihwan.jetpackpro.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import id.ihwan.jetpackpro.R
import id.ihwan.jetpackpro.movies.view.MoviesFragment
import id.ihwan.jetpackpro.tvshow.view.TvShowFragment
import kotlinx.android.synthetic.main.activity_main.*
import id.ihwan.jetpackpro.favorite.FavoriteActivity

class HomeActivity : AppCompatActivity() {

    /*
    * TODO : Dapat menyimpan film ke database favorite.
    * TODO : Dapat menghapus film dari database favorite.
    * TODO : Terdapat halaman untuk menampilkan daftar Favorite Movies.
    * TODO : Terdapat halaman untuk menampilkan daftar Favorite Tv Show.
    * TODO : Menerapkan Room menyimpan data Favorite Movie dan Favorite Tv Show.
    * TODO : Menerapkan Pagination untuk mengatur data pada RecyclerView.
    */

    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewPager()
    }

    private fun initViewPager() {
        viewPager = homeViewPager
        viewPager.adapter = HomePagerAdapter(supportFragmentManager)
        homeTabLayout.setupWithViewPager(viewPager)
    }


    inner class HomePagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
        private var moviesFragment: MoviesFragment = MoviesFragment.newInstance()
        private var tvShowFragment: TvShowFragment = TvShowFragment.newInstance()

        override fun getCount(): Int = 2

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> moviesFragment
                else -> tvShowFragment
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "MOVIES"
                else -> "TV SHOW"
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.itemId

        if (id == R.id.favorite) {
            startActivity(Intent(this, FavoriteActivity::class.java))
            return true
        }

        return super.onOptionsItemSelected(item)

    }

}
