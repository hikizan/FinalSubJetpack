package com.hikizan.myapplication.utils;

import com.hikizan.myapplication.model.source.local.entity.MovieDbModel;
import com.hikizan.myapplication.model.source.remote.response.MovieDbResponse;

import java.util.ArrayList;

public class DummyData {
    public static ArrayList<MovieDbModel> generateDummyMovies() {

        ArrayList<MovieDbModel> movies = new ArrayList<>();

        movies.add(new MovieDbModel("movies1",
                "Hard Kill",
                "10/23/2020",
                "R",
                "44%",
                "Action, Thriller",
                "The work of billionaire tech CEO Donovan Chalmers is so valuable that he hires mercenaries to protect it, and a terrorist group kidnaps his daughter just to get it.",
                "1h 38m",
                "https://www.themoviedb.org/movies/724989-hard-kill",
                "movie1"));

        movies.add(new MovieDbModel("movies2",
                "2067",
                "10/02/2020",
                "16+",
                "53%",
                "Science Fiction, Thriller, Drama",
                "A lowly utility worker is called to the future by a mysterious radio signal, he must leave his dying wife to embark on a journey that will force him to face his deepest fears in an attempt to change the fabric of reality and save humankind from its greatest environmental crisis yet.",
                "1h 54m",
                "https://www.themoviedb.org/movies/528085-2067",
                "movie2"));

        movies.add(new MovieDbModel("movies3",
                "Demon Slayer: Kimetsu no Yaiba - The Movie: Mugen Train",
                "10/16/2020",
                "PG12",
                "70%",
                "Animation, Action, History, Adventure, Fantasy, Drama",
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                "1h 57m",
                "https://www.themoviedb.org/movies/635302",
                "movie3"));

        movies.add(new MovieDbModel("movies4",
                "Ava",
                "09/25/2020",
                "R",
                "58%",
                "Action, Crime, Drama, Thriller",
                "A black ops assassin is forced to fight for her own survival after a job goes dangerously wrong.",
                "1h 36m",
                "https://www.themoviedb.org/movies/539885-ava",
                "movie4"));

        movies.add(new MovieDbModel("movies5",
                "The War with Grandpa",
                "10/09/2020",
                "PG",
                "62%",
                "Comedy, Family, Drama",
                "Sixth-grader Peter is pretty much your average kid—he likes gaming, hanging with his friends and his beloved pair of Air Jordans. But when his recently widowed grandfather Ed moves in with Peter’s family, the boy is forced to give up his most prized possession of all, his bedroom. Unwilling to let such an injustice stand, Peter devises a series of increasingly elaborate pranks to drive out the interloper, but Grandpa Ed won’t go without a fight.",
                "1h 34m",
                "https://www.themoviedb.org/movies/425001-the-war-with-grandpa",
                "movie5"));

        movies.add(new MovieDbModel("movies6",
                "The Craft: Legacy",
                "10/28/2020",
                "PG-13",
                "68%",
                "Horror, Drama, Fantasy",
                "Sixth-grader Peter is pretty much your average kid—he likes gaming, hanging with his friends and his beloved pair of Air Jordans. But when his recently widowed grandfather Ed moves in with Peter’s family, the boy is forced to give up his most prized possession of all, his bedroom. Unwilling to let such an injustice stand, Peter devises a series of increasingly elaborate pranks to drive out the interloper, but Grandpa Ed won’t go without a fight.",
                "1h 34m",
                "https://www.themoviedb.org/movies/590995-the-craft-legacy",
                "movie6"));

        movies.add(new MovieDbModel("movies7",
                "Bill & Ted Face the Music",
                "08/28/2020",
                "PG-13",
                "64%",
                "Comedy, Science Fiction, Adventure",
                "Yet to fulfill their rock and roll destiny, the now middle-aged best friends Bill and Ted set out on a new adventure when a visitor from the future warns them that only their song can save life as we know it. Along the way, they are helped by their daughters, a new batch of historical figures and a few music legends—to seek the song that will set their world right and bring harmony to the universe.",
                "1h 32m",
                "https://www.themoviedb.org/movies/501979-bill-ted-face-the-music",
                "movie7"));

        movies.add(new MovieDbModel("movies8",
                "The Exorcist",
                "12/26/1973",
                "R",
                "77%",
                "Horror",
                "12-year-old Regan MacNeil begins to adapt an explicit new personality as strange events befall the local area of Georgetown. Her mother becomes torn between science and superstition in a desperate bid to save her daughter, and ultimately turns to her last hope: Father Damien Karras, a troubled priest who is struggling with his own faith.",
                "2h 2m",
                "https://www.themoviedb.org/movies/9552-the-exorcist",
                "movie8"));

        movies.add(new MovieDbModel("movies9",
                "The Empty Man",
                "10/23/2020",
                "R",
                "54%",
                "Crime, Drama, Horror",
                "After a group of teens from a small Midwestern town begin to mysteriously disappear, the locals believe it is the work of an urban legend known as The Empty Man. As a retired cop investigates and struggles to make sense of the stories, he discovers a secretive group and their attempts to summon a horrific, mystical entity, and soon his life—and the lives of those close to him—are in grave danger.",
                "2h 17m",
                "https://www.themoviedb.org/movies/516632-the-empty-man",
                "movie9"));

        movies.add(new MovieDbModel("movies10",
                "Honest Thief",
                "10/09/2020",
                "PG-13",
                "67%",
                "Action, Thriller, Crime, Drama",
                "A bank robber tries to turn himself in because he's falling in love and wants to live an honest life...but when he realizes the Feds are more corrupt than him, he must fight back to clear his name.",
                "1h 38m",
                "https://www.themoviedb.org/movies/553604-the-honest-thief",
                "movie10"));

        return movies;
    }

    public static ArrayList<MovieDbModel> generateDummyTvShows() {

        ArrayList<MovieDbModel> tvShows = new ArrayList<>();

        tvShows.add(new MovieDbModel("tvShows1",
                "Given",
                "07/11/2019",
                "M",
                "94%",
                "Animation, Drama",
                "The played chord made the rusty air and both our eardrums vibrate smoothly. From that moment... our music was born.",
                "23m",
                "https://www.themoviedb.org/tv/88040",
                "tv11"));

        tvShows.add(new MovieDbModel("tvShows2",
                "The Rising of the Shield Hero",
                "01/09/2019",
                "TV-14",
                "90%",
                "Animation, Action & Adventure, Sci-Fi & Fantasy, Drama",
                "Iwatani Naofumi was summoned into a parallel world along with 3 other people to become the world's Heroes. Each of the heroes respectively equipped with their own legendary equipment when summoned, Naofumi received the Legendary Shield as his weapon. Due to Naofumi's lack of charisma and experience he's labeled as the weakest, only to end up betrayed, falsely accused, and robbed by on the third day of adventure. Shunned by everyone from the king to peasants, Naofumi's thoughts were filled with nothing but vengeance and hatred. Thus, his destiny in a parallel World begins...",
                "24m",
                "https://www.themoviedb.org/tv/83095",
                "tv12"));

        tvShows.add(new MovieDbModel("tvShows3",
                "My Hero Academia",
                "04/03/2016",
                "TV-14",
                "89%",
                "Action & Adventure, Animation, Comedy",
                "In a world where eighty percent of the population has some kind of super-powered Quirk, Izuku was unlucky enough to be born completely normal. But that won’t stop him from enrolling in a prestigious hero academy. Now, he’ll get his first taste of brutal rivalry from other schools as he braves the cutthroat, no-holds-barred provisional license exam.",
                "24m",
                "https://www.themoviedb.org/tv/65930",
                "tv13"));

        tvShows.add(new MovieDbModel("tvShows4",
                "Rent-a-Girlfriend",
                "07/11/2020",
                "TV-14",
                "89%",
                "Comedy, Animation",
                "In today’s Japan, \"rental\" services can deliver an afternoon with a \"friend,\" a \"parent,\" even a fake girlfriend! Kinoshita Kazuya is a 20-year-old failure of a college student. He managed to kiss his girlfriend once, but was dumped after a month. Completely spiteful, Kazuya gets just desperate enough to give it a try. But he quickly discovers how complicated it can be to \"rent\" an emotional connection… and his new \"girlfriend,\" Mizuhara Chizuru, who’s trying to keep her side hustle secret, will panic when she finds out her real life and Kazuya’s are intertwined in surprising ways! A reckless rom-com filled with love and excitement is about to begin!",
                "24m",
                "https://www.themoviedb.org/tv/96316",
                "tv14"));

        tvShows.add(new MovieDbModel("tvShows5",
                "The Promised Neverland",
                "01/09/2019",
                "TV-MA",
                "89%",
                "Animation, Mystery, Sci-Fi & Fantasy, Action & Adventure, Drama",
                "The one adored as the mother is not the real parent. The people living here together are not actual siblings. The Gracefield House is where orphaned children live. An irreplaceable home where 38 siblings and Mom live happy lives, even with no blood relations. However, their everyday life suddenly came to an abrupt end one day...",
                "23m",
                "https://www.themoviedb.org/tv/83097",
                "tv15"));

        tvShows.add(new MovieDbModel("tvShows6",
                "The Promised Neverland",
                "01/09/2019",
                "TV-MA",
                "89%",
                "Animation, Mystery, Sci-Fi & Fantasy, Action & Adventure, Drama",
                "The one adored as the mother is not the real parent. The people living here together are not actual siblings. The Gracefield House is where orphaned children live. An irreplaceable home where 38 siblings and Mom live happy lives, even with no blood relations. However, their everyday life suddenly came to an abrupt end one day...",
                "24m",
                "https://www.themoviedb.org/tv/83097",
                "tv16"));

        tvShows.add(new MovieDbModel("tvShows7",
                "Kaguya-sama: Love is War",
                "01/12/2019",
                "TV-14",
                "89%",
                "Comedy, Animation, Drama",
                "Considered a genius due to having the highest grades in the country, Miyuki Shirogane leads the prestigious Shuchiin Academy's student council as its president, working alongside the beautiful and wealthy vice president Kaguya Shinomiya. The two are often regarded as the perfect couple by students despite them not being in any sort of romantic relationship.",
                "24m",
                "https://www.themoviedb.org/tv/83121",
                "tv17"));

        tvShows.add(new MovieDbModel("tvShows8",
                "DARLING in the FRANXX",
                "01/13/2018",
                "TV-14",
                "89%",
                "Action & Adventure, Animation, Sci-Fi & Fantasy",
                "The story is set in the distant future. The land is ruined, and humanity establishes the mobile fort city Plantation. Pilots produced inside Plantation live in Mistilteinn, also know as the \"birdcage.\" Children live there knowing nothing of the outside world or the freedom of the sky. Their lives consist of battling to carry out missions. Their enemies are mysterious giant lifeforms known as Kyouryuu, and the children pilot robots called Franxx to face off against them. For the children, riding the Franxx proves their existence.\n" +
                        "\t\t  A boy named Hiro is called Code:016, and he was once known as a prodigy. However, he has fallen behind, and his existence seems unnecessary. Not piloting a Franxx is the same as ceasing to exist. One day, a mysterious girl known as \"Zero Two\" appears before him. Two horns grow out of her head.",
                "24m",
                "https://www.themoviedb.org/tv/76121",
                "tv18"));

        tvShows.add(new MovieDbModel("tvShows9",
                "Demon Slayer: Kimetsu no Yaiba",
                "04/06/2019",
                "TV-MA",
                "89%",
                "Animation, Drama, Sci-Fi & Fantasy, Action & Adventure",
                "It is the Taishō period in Japan. Tanjirō, a kindhearted boy who sells charcoal for a living, finds his family slaughtered by a demon. To make matters worse, his younger sister Nezuko, the sole survivor, has been transformed into a demon herself. Though devastated by this grim reality, Tanjirō resolves to become a “demon slayer” so that he can turn his sister back into a human, and kill the demon that massacred his family.",
                "24m",
                "https://www.themoviedb.org/tv/85937",
                "tv19"));

        tvShows.add(new MovieDbModel("tvShows10",
                "Your Lie in April",
                "08/10/2014",
                "TV-PG",
                "89%",
                "Animation, Comedy, Drama",
                "Kousei Arima was a genius pianist until his mother's sudden death took away his ability to play. Each day was dull for Kousei. But, then he meets a violinist named Kaori Miyazono who has an eccentric playing style. Can the heartfelt sounds of the girl's violin lead the boy to play the piano again?",
                "24m",
                "https://www.themoviedb.org/tv/61663",
                "tv20"));

        return tvShows;
    }

    public static ArrayList<MovieDbResponse> generateRemoteDummyMovies() {

        ArrayList<MovieDbResponse> movies = new ArrayList<>();

        movies.add(new MovieDbResponse("movies1",
                "Hard Kill",
                "10/23/2020",
                "R",
                "44%",
                "Action, Thriller",
                "The work of billionaire tech CEO Donovan Chalmers is so valuable that he hires mercenaries to protect it, and a terrorist group kidnaps his daughter just to get it.",
                "1h 38m",
                "https://www.themoviedb.org/movies/724989-hard-kill",
                "movie1"));

        movies.add(new MovieDbResponse("movies2",
                "2067",
                "10/02/2020",
                "16+",
                "53%",
                "Science Fiction, Thriller, Drama",
                "A lowly utility worker is called to the future by a mysterious radio signal, he must leave his dying wife to embark on a journey that will force him to face his deepest fears in an attempt to change the fabric of reality and save humankind from its greatest environmental crisis yet.",
                "1h 54m",
                "https://www.themoviedb.org/movies/528085-2067",
                "movie2"));

        movies.add(new MovieDbResponse("movies3",
                "Demon Slayer: Kimetsu no Yaiba - The Movie: Mugen Train",
                "10/16/2020",
                "PG12",
                "70%",
                "Animation, Action, History, Adventure, Fantasy, Drama",
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                "1h 57m",
                "https://www.themoviedb.org/movies/635302",
                "movie3"));

        movies.add(new MovieDbResponse("movies4",
                "Ava",
                "09/25/2020",
                "R",
                "58%",
                "Action, Crime, Drama, Thriller",
                "A black ops assassin is forced to fight for her own survival after a job goes dangerously wrong.",
                "1h 36m",
                "https://www.themoviedb.org/movies/539885-ava",
                "movie4"));

        movies.add(new MovieDbResponse("movies5",
                "The War with Grandpa",
                "10/09/2020",
                "PG",
                "62%",
                "Comedy, Family, Drama",
                "Sixth-grader Peter is pretty much your average kid—he likes gaming, hanging with his friends and his beloved pair of Air Jordans. But when his recently widowed grandfather Ed moves in with Peter’s family, the boy is forced to give up his most prized possession of all, his bedroom. Unwilling to let such an injustice stand, Peter devises a series of increasingly elaborate pranks to drive out the interloper, but Grandpa Ed won’t go without a fight.",
                "1h 34m",
                "https://www.themoviedb.org/movies/425001-the-war-with-grandpa",
                "movie5"));

        movies.add(new MovieDbResponse("movies6",
                "The Craft: Legacy",
                "10/28/2020",
                "PG-13",
                "68%",
                "Horror, Drama, Fantasy",
                "Sixth-grader Peter is pretty much your average kid—he likes gaming, hanging with his friends and his beloved pair of Air Jordans. But when his recently widowed grandfather Ed moves in with Peter’s family, the boy is forced to give up his most prized possession of all, his bedroom. Unwilling to let such an injustice stand, Peter devises a series of increasingly elaborate pranks to drive out the interloper, but Grandpa Ed won’t go without a fight.",
                "1h 34m",
                "https://www.themoviedb.org/movies/590995-the-craft-legacy",
                "movie6"));

        movies.add(new MovieDbResponse("movies7",
                "Bill & Ted Face the Music",
                "08/28/2020",
                "PG-13",
                "64%",
                "Comedy, Science Fiction, Adventure",
                "Yet to fulfill their rock and roll destiny, the now middle-aged best friends Bill and Ted set out on a new adventure when a visitor from the future warns them that only their song can save life as we know it. Along the way, they are helped by their daughters, a new batch of historical figures and a few music legends—to seek the song that will set their world right and bring harmony to the universe.",
                "1h 32m",
                "https://www.themoviedb.org/movies/501979-bill-ted-face-the-music",
                "movie7"));

        movies.add(new MovieDbResponse("movies8",
                "The Exorcist",
                "12/26/1973",
                "R",
                "77%",
                "Horror",
                "12-year-old Regan MacNeil begins to adapt an explicit new personality as strange events befall the local area of Georgetown. Her mother becomes torn between science and superstition in a desperate bid to save her daughter, and ultimately turns to her last hope: Father Damien Karras, a troubled priest who is struggling with his own faith.",
                "2h 2m",
                "https://www.themoviedb.org/movies/9552-the-exorcist",
                "movie8"));

        movies.add(new MovieDbResponse("movies9",
                "The Empty Man",
                "10/23/2020",
                "R",
                "54%",
                "Crime, Drama, Horror",
                "After a group of teens from a small Midwestern town begin to mysteriously disappear, the locals believe it is the work of an urban legend known as The Empty Man. As a retired cop investigates and struggles to make sense of the stories, he discovers a secretive group and their attempts to summon a horrific, mystical entity, and soon his life—and the lives of those close to him—are in grave danger.",
                "2h 17m",
                "https://www.themoviedb.org/movies/516632-the-empty-man",
                "movie9"));

        movies.add(new MovieDbResponse("movies10",
                "Honest Thief",
                "10/09/2020",
                "PG-13",
                "67%",
                "Action, Thriller, Crime, Drama",
                "A bank robber tries to turn himself in because he's falling in love and wants to live an honest life...but when he realizes the Feds are more corrupt than him, he must fight back to clear his name.",
                "1h 38m",
                "https://www.themoviedb.org/movies/553604-the-honest-thief",
                "movie10"));

        return movies;
    }
}
