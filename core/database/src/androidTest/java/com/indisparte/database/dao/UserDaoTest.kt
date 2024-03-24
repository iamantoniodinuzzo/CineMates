package com.indisparte.database.dao

import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.indisparte.base.MediaType
import com.indisparte.database.dao.base.BaseDaoTest
import com.indisparte.database.entity.GenreEntity
import com.indisparte.database.entity.relations.UserFavActorCrossRef
import com.indisparte.database.entity.relations.UserFavGenreCrossRef
import com.indisparte.database.entity.relations.UserFavMediaCrossRef
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date


/**
 *@author Antonio Di Nuzzo
 */
@RunWith(AndroidJUnit4ClassRunner::class)
@SmallTest
class UserDaoTest : BaseDaoTest() {

    private lateinit var userDao: UserDao
    private lateinit var genreDao: GenreDao
    private lateinit var actorDao: ActorDao
    private lateinit var mediaDao: MediaDao
    private lateinit var defaultFavGenre: GenreEntity


    override fun setup() {
        super.setup()
        userDao = testDatabase.userDao()
        actorDao = testDatabase.personDao()
        genreDao = testDatabase.genreDao()
        mediaDao = testDatabase.mediaDao()
        defaultFavGenre =
            GenreEntity(genreId = 3171, name = "Carey Hays", mediaType = MediaType.MOVIE.id)
        genreDao.insert(defaultFavGenre)
        actorDao.insert(defaultActorEntity)
        userDao.insert(defaultUserEntity)
        mediaDao.insert(defaultMediaEntity)
    }

    //TEST FAV ACTOR TEST
    @Test
    fun getUserFavActors_successfully() {
        //GIVEN - tutto quello che ti serve per testare
        userDao.insertUserFavActorCrossRef(
            UserFavActorCrossRef(
                actorId = defaultActorEntity.actorId,
                userId = defaultUserEntity.userId,
                favDate = Date(System.currentTimeMillis())
            )
        )

        //WHEN - le azioni e i cambiamenti del soggetto del test
        val result = userDao.getUserFavActors(defaultUserEntity.userId)

        //THEN - verifica se il test è andato come ti aspettavi
        assertEquals(result[0].user, defaultUserEntity)
        assert(result[0].actors.contains(defaultActorEntity))

    }


    @Test
    fun deleteUserFavActor_successfully() {
        //GIVEN - tutto quello che ti serve per testare
        userDao.insertUserFavActorCrossRef(
            UserFavActorCrossRef(
                actorId = defaultActorEntity.actorId,
                userId = defaultUserEntity.userId,
                favDate = Date(System.currentTimeMillis())
            )
        )
        val resultBeforeDeletion = userDao.getUserFavActor(
            userId = defaultUserEntity.userId,
            actorId = defaultActorEntity.actorId
        )

        //WHEN - le azioni e i cambiamenti del soggetto del test
        val result = resultBeforeDeletion?.let { userDao.deleteUserFavActorCrossRef(it) }
        val resultAfterDeletion = userDao.getUserFavActor(
            userId = defaultUserEntity.userId,
            actorId = defaultActorEntity.actorId
        )

        //THEN - verifica se il test è andato come ti aspettavi
        assertNotNull(resultBeforeDeletion)
        assertEquals(1, result)
        assertNull(resultAfterDeletion)

    }

    //USER FAV GENRES

    @Test
    fun getUserFavGenres_successfully() {
        //GIVEN - tutto quello che ti serve per testare
        userDao.insertUserFavGenreCrossRef(
            UserFavGenreCrossRef(
                userId = defaultUserEntity.userId,
                genreId = defaultFavGenre.genreId,
                favDate = Date(System.currentTimeMillis())
            )
        )

        //WHEN - le azioni e i cambiamenti del soggetto del test
        val result = userDao.getUserFavGenres(defaultUserEntity.userId)

        //THEN - verifica se il test è andato come ti aspettavi
        assertEquals(result[0].user, defaultUserEntity)
        assert(result[0].genres.contains(defaultFavGenre))

    }


    @Test
    fun deleteUserFavGenre_successfully() {
        //GIVEN - tutto quello che ti serve per testare
        userDao.insertUserFavGenreCrossRef(
            UserFavGenreCrossRef(
                userId = defaultUserEntity.userId,
                genreId = defaultFavGenre.genreId,
                favDate = Date(System.currentTimeMillis())
            )
        )

        val resultBeforeDeletion = userDao.getUserFavGenre(
            userId = defaultUserEntity.userId,
            genreId = defaultFavGenre.genreId
        )

        //WHEN - le azioni e i cambiamenti del soggetto del test
        val result = resultBeforeDeletion?.let { userDao.deleteUserFavGenreCrossRef(it) }
        val resultAfterDeletion = userDao.getUserFavGenre(
            userId = defaultUserEntity.userId,
            genreId = defaultFavGenre.genreId
        )

        //THEN - verifica se il test è andato come ti aspettavi
        assertNotNull(resultBeforeDeletion)
        assertEquals(1, result)
        assertNull(resultAfterDeletion)

    }


    //USER FAV MEDIA
    @Test
    fun getUserFavMedias_successfully() {
        //GIVEN - tutto quello che ti serve per testare
        userDao.insertUserFavMediaCrossRef(
            UserFavMediaCrossRef(
                mediaId = defaultMediaEntity.mediaId,
                userId = defaultUserEntity.userId,
                favDate = Date(System.currentTimeMillis())
            )
        )

        //WHEN - le azioni e i cambiamenti del soggetto del test
        val result = userDao.getUserFavMedias(defaultUserEntity.userId)

        //THEN - verifica se il test è andato come ti aspettavi
        assertEquals(result[0].user, defaultUserEntity)
        assert(result[0].favMedias.contains(defaultMediaEntity))

    }


    @Test
    fun deleteUserFavMedia_successfully() {
        //GIVEN - tutto quello che ti serve per testare
        userDao.insertUserFavMediaCrossRef(
            UserFavMediaCrossRef(
                mediaId = defaultMediaEntity.mediaId,
                userId = defaultUserEntity.userId,
                favDate = Date(System.currentTimeMillis())
            )
        )

        val resultBeforeDeletion = userDao.getUserFavMedia(
            userId = defaultUserEntity.userId,
            mediaId = defaultMediaEntity.mediaId
        )

        //WHEN - le azioni e i cambiamenti del soggetto del test
        val result = resultBeforeDeletion?.let { userDao.deleteUserFavMediaCrossRef(it) }
        val resultAfterDeletion = userDao.getUserFavMedia(
            userId = defaultUserEntity.userId,
            mediaId = defaultMediaEntity.mediaId
        )


        //THEN - verifica se il test è andato come ti aspettavi
        assertNotNull(resultBeforeDeletion)
        assertEquals(1, result)
        assertNull(resultAfterDeletion)

    }


    //USER DEFAULT LISTS
    //TODO: Metodi da testare


}