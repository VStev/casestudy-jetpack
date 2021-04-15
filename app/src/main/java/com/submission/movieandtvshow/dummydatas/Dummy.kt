package com.submission.movieandtvshow.dummydatas

import com.submission.movieandtvshow.R
import com.submission.movieandtvshow.dataobjects.Movie
import com.submission.movieandtvshow.dataobjects.TVShow

object Dummy {
    fun generateShows(): ArrayList<TVShow> {
        val shows = ArrayList<TVShow>()
        shows.add(
            TVShow(
                "TV01",
                "Arrow",
                "2012",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                false,
                178,
                9,
                R.drawable.poster_arrow
            )
        )
        shows.add(
            TVShow(
                "TV02",
                "Constantine",
                "2014",
                "A man struggling with his faith is haunted by the sins of his past but is suddenly thrust into the role of defending humanity from the gathering forces of darkness.",
                false,
                13,
                1,
                R.drawable.poster_constantine
            )
        )
        shows.add(
            TVShow(
                "TV03",
                "Formula 1: Drive to Survive",
                "2019",
                "Drivers, managers and team owners live life in the fast lane -- both on and off the track -- during one cutthroat season of Formula 1 racing.",
                true,
                30,
                3,
                R.drawable.poster_drive_to_survive
            )
        )
        shows.add(
            TVShow(
                "TV04",
                "Lucifer",
                "2016",
                "It's good to be bad.\nBored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                true,
                85,
                6,
                R.drawable.poster_lucifer
            )
        )
        shows.add(
            TVShow(
                "TV05",
                "NCIS",
                "2003",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                false,
                486,
                19,
                R.drawable.poster_ncis
            )
        )
        shows.add(
            TVShow(
                "TV06",
                "Pacific Rim: The Black",
                "2021",
                "The only way home is through the Black.\nTwo siblings - an idealistic teenage boy and his naïve younger sister - are forced to pilot an abandoned Jaeger across a hostile landscape in a desperate attempt to find their missing parents.",
                true,
                7,
                2,
                R.drawable.poster_pacific_rim
            )
        )
        shows.add(
            TVShow(
                "TV07",
                "Sword Art Online Alternative: Gun Gale Online",
                "2018",
                "A shy university student in Tokyo, Karen Kohiruimaki stands in stark contrast to her in-game avatar—in fact, she happens to stand above everyone else too, much to her dismay. Towering above all the people around her, Karen's insecurities over her height reach the point where she turns to the virtual world for an escape. Starting game after game in hopes of manifesting as a cute, short character, she finally obtains her ideal self in the world of Gun Gale Online. Overjoyed by her new persona, she pours her time into the game as LLENN, garnering her reputation as the legendary player killer. However, when one of LLENN's targets gets the best of her, she ends up meeting Pitohui, a skilled yet eccentric woman. Pitohui insists that LLENN participates in Squad Jam, a battle royale. Thrust into the heated competition, LLENN must fight with all her wit and will if she hopes to shoot her way to the top.",
                false,
                13,
                2,
                R.drawable.poster_sao_alternative_ggo
            )
        )
        shows.add(
            TVShow(
                "TV08",
                "The Flash",
                "2014",
                "The fastest man alive.\nAfter a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                true,
                149,
                8,
                R.drawable.poster_flash
            )
        )
        shows.add(
            TVShow(
                "TV09",
                "The Simpsons",
                "1989",
                "On your marks, get set, d'oh!\nSet in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                true,
                702,
                33,
                R.drawable.poster_the_simpson
            )
        )
        shows.add(
            TVShow(
                "TV10",
                "The Walking Dead",
                "2010",
                "Fight the dead. Fear the living.\nSheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                true,
                200,
                12,
                R.drawable.poster_the_walking_dead
            )
        )
        shows.add(
            TVShow(
                null,
                "Faulty Data",
                "2048",
                "Fight the dead. Fear the living.\nSheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                true,
                200,
                12,
                R.drawable.poster_hathaway
            )
        )
        return shows
    }

    fun generateMovies(): ArrayList<Movie> {
        val movies = ArrayList<Movie>()
        movies.add(
            Movie(
                "M01",
                "Alita Battle Angel",
                "14 February 2019",
                "Robert Rodriguez",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                R.drawable.poster_alita
            )
        )
        movies.add(
            Movie(
                "M02",
                "Aquaman",
                "21 December 2018",
                "James Wan",
                "Home Is Calling\nOnce home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                R.drawable.poster_aquaman
            )
        )
        movies.add(
            Movie(
                "M03",
                "Bohemian Rhapsody",
                "24 October 2018",
                "Anthony McCarten",
                "Fearless lives forever\nSinger Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                R.drawable.poster_bohemian
            )
        )
        movies.add(
            Movie(
                "M04",
                "Deadpool 2",
                "18 May 2018",
                "Fabian Nicieza",
                "Prepare for the Second Coming.\nWisecracking mercenary Deadpool battles the evil and powerful Cable and other bad guys to save a boy's life.",
                R.drawable.poster_deadpool2
            )
        )
        movies.add(
            Movie(
                "M05",
                "How to Train Your Dragon: The Hidden World",
                "22 February 2019",
                "Dean DeBlois",
                "The friendship of a lifetime\nAs Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                R.drawable.poster_how_to_train
            )
        )
        movies.add(
            Movie(
                "M06",
                "Avengers: Infinity War",
                "27 April 2018",
                "Joe Russo",
                "An entire universe. Once and for all.\nAs the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                R.drawable.poster_infinity_war
            )
        )
        movies.add(
            Movie(
                "M07",
                "Kono Subarashii Sekai ni Shukufuku Wo! Kurenai Densetsu",
                "30 August 2019",
                "Takaomi Kanasaki",
                "We will definitely return alive. Go back to that place.\nIt is not strange that the Demon Lord's forces fear the Crimson Demons, the clan from which Megumin and Yunyun originate. Even if the Demon Lord's generals attack their village, the Crimson Demons can just easily brush them off with their supreme mastery of advanced and overpowered magic. When Yunyun receives a seemingly serious letter regarding a potential disaster coming to her hometown, she immediately informs Kazuma Satou and the rest of his party. After a series of wacky misunderstandings, it turns out to be a mere prank by her fellow demon who wants to be an author. Even so, Megumin becomes worried about her family and sets out toward the Crimson Demons' village with the gang. There, Kazuma and the others decide to sightsee the wonders of Megumin's birthplace. However, they soon come to realize that the nonsense threat they received might have been more than just a joke.",
                R.drawable.poster_konosuba
            )
        )
        movies.add(
            Movie(
                "M08",
                "Overlord",
                "9 November 2011",
                "Billy Ray",
                "Stop the unstoppable\nFrance, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small village near the beaches of Normandy. After reaching their target, the surviving paratroopers realise that, in addition to fighting the Nazi troops that patrol the village, they also must fight against something else.",
                R.drawable.poster_overlord
            )
        )
        movies.add(
            Movie(
                "M09",
                "I Want to Eat Your Pancreas",
                "1 September 2018",
                "Shinichiro Ushijima",
                "This is a story about an unforgettable spring...\nSpring time in April and the last of the cherry blossoms are still in bloom. The usually aloof bookworm with no interest in others comes across a book in a hospital waiting room. Handwritten on the cover are the words: \"Living with Dying.\" He soon discovers that it is a diary kept by his very popular and genuinely cheerful classmate, Sakura Yamauchi, who reveals to him that she is secretly suffering from a pancreatic illness and only has a limited time left. It is at this moment that she gains just one more person to share her secret. Trying to maintain a normal life as much as possible, Sakura is determined to live her life to the fullest until the very last day. As her free spirit and unpredictable actions throw him for a loop, his heart begins to gradually change.",
                R.drawable.poster_pancreas
            )
        )
        movies.add(
            Movie(
                "M10",
                "Spiderman: Into the Spider-Verse",
                "14 December 2018",
                "Rodney Rothman",
                "More Than One Wears the Mask\nMiles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                R.drawable.poster_spiderman
            )
        )
        movies.add(
            Movie(
                null,
                "Faulty Data",
                "30 June 2049",
                "Rodney Rothman",
                "More Than One Wears the Mask\nMiles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                R.drawable.poster_2049
            )
        )
        return movies
    }
}