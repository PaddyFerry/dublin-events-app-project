

```python
import database
```


```python
import entertainment
```


```python
import eventbrite
```


```python
import runner
```


```python
db = database.Database("test", "1234", "159.65.84.145", "app")
```


```python
with db:
    print db.select("Fibber Magee's" , "name", "venuesTest")
```

    Connected to database
    SELECT * FROM venuesTest WHERE name = "Fibber Magees" OR REPLACE(name, "'", "") = "Fibber Magees"
    Finished with database
    


```python
with db:
    print db.check_db_venue("fibber magees")
    print db.check_db_venue("3arena")
    print db.check_db_venue("THE WORKMANS CLUB")
    print db.check_db_venue("  whelans  ")
```

    Connected to database
    1
    1
    1
    1
    Finished with database
    


```python
with db:
    places = []
    places.append(db.google_it("fibber magees"))
    places.append(db.google_it("3 arena"))
    places.append(db.google_it("national concert hall"))
    places.append(db.google_it("the button factory"))
for place in places:
    print place
```

    Connected to database
    Finished with database
    (u'Fibber Magees', u'4.4', u'Bar', u'https://lh5.googleusercontent.com/p/AF1QipNg3rh_ZC7BedDdnppcXxu0VXlUhZqHT31ulNbM=w240-h160-k-no', u'80-81 Parnell St, Rotunda, Dublin 1, D01 CK74', u'Classic pub with a predilection for hard guitar music with brick walls, pool tables and beer garden.', -6.2606074, 53.35277259999999)
    (u'3Arena', u'4.4', u'Live Music Venue', u'https://lh5.googleusercontent.com/p/AF1QipOzfDINvH6XTmtWKw2YFYknL4gwr2NqnSQN6Kzm=w285-h160-k-no', u'N Wall Quay, North Dock, Dublin 1', u'Live Music Venue', -6.2450651, 53.3481341)
    (u'National Concert Hall, Dublin', u'4.6', u'Concert hall ', u'https://lh5.googleusercontent.com/p/AF1QipPao7I_2pCz1Vie74COCPXnuow3eT-ZiJGc3vGy=w240-h160-k-no', u"Earlsfort Terrace, Saint Kevin's, Dublin, D02 N527", u'Concert hall ', -6.2582318, 53.3345281)
    (u'Button Factory', u'4.1', u'Event Venue', u'https://lh5.googleusercontent.com/p/AF1QipPhgGdYrQpUSgD75wJWEHG-shBusyuaKpr0Mkyx=w213-h160-k-no', u'Curved St, Temple Bar, Dublin', u'Intimate, buzzing live music and clubbing venue staging innovative bands and DJ sets.', -6.2645458, 53.3448613)
    


```python
database.Database.fix_category("Event Venue in Dublin, Republic of Ireland")
```




    'Event Venue '




```python
with db:
    print db.add_to_db_venue(places[0])
```

    Connected to database
    None
    Finished with database
    


```python
print db.output
```

    Error: 1062 (23000): Duplicate entry 'Fibber Magees-80-81 Parnell St, Rotunda, Dublin 1, D01 CK74' for key 'PRIMARY'
    
    


```python
with db:
    print db.find_image(places[3][0])
```

    Connected to database
    https://lh5.googleusercontent.com/p/AF1QipPhgGdYrQpUSgD75wJWEHG-shBusyuaKpr0Mkyx=w213-h160-k-no
    Finished with database
    


```python
entertainment.get_date_time("Mon 12 March 2018")
```




    '2018-3-12 :'




```python
links =[]
for link in entertainment.get_links():
    print link
    links.append(link)
```

    http://www.entertainment.ie/show-Dublin/National-Concert-Hall/The-Gloaming/Music-2808829.htm
    http://www.entertainment.ie/show-Dublin/The-Complex/Where-We-Live-presented-by-THISISPOPBABY/Music-2809048.htm
    http://www.entertainment.ie/show-Dublin/The-Sugar-Club/Waka-Flocka-Flame/Music-2807585.htm
    http://www.entertainment.ie/show-Dublin/The-Complex/Where-We-Live-The-High-Hopes-Community-Choir/Music-2809132.htm
    http://www.entertainment.ie/show-Dublin/Gaiety-Theatre/Thriller-Live/Music-2813608.htm
    http://www.entertainment.ie/show-Dublin/International-Bar-Dublin/The-Circle-Sessions/Music-2809250.htm
    http://www.entertainment.ie/show-Dublin/Olympia-Theatre/Rejjie-Snow/Music-2806168.htm
    http://www.entertainment.ie/show-Dublin/The-Dolmen-Theatre/Goodnight-Delia/Music-2814046.htm
    http://www.entertainment.ie/show-Dublin/The-Complex/Where-We-Live-The-Mouth-of-a-Shark/Music-2809055.htm
    http://www.entertainment.ie/show-Dublin/The-Complex/Where-We-Live-presented-by-THISISPOPBABY/Music-2809048.htm
    http://www.entertainment.ie/show-Dublin/The-Complex/Where-We-Live-The-High-Hopes-Community-Choir/Music-2809132.htm
    http://www.entertainment.ie/show-Dublin/Gaiety-Theatre/Thriller-Live/Music-2813608.htm
    http://www.entertainment.ie/show-Dublin/The-Dolmen-Theatre/Goodnight-Delia/Music-2814046.htm
    http://www.entertainment.ie/show-Dublin/The-Complex/Where-We-Live-The-Mouth-of-a-Shark/Music-2809055.htm
    http://www.entertainment.ie/show-Dublin/Olympia-Theatre/Erasure/Music-2813726.htm
    http://www.entertainment.ie/show-Dublin/Doyles/The-Ruby-Sessions/Music-2810584.htm
    http://www.entertainment.ie/show-Dublin/Whelans/LÃœ-TYSKY-upstairs/Music-2814099.htm
    http://www.entertainment.ie/show-Dublin/Hapenny-Bridge-Inn/Battle-of-the-Axe/Music-2812529.htm
    http://www.entertainment.ie/show-Dublin/The-Complex/Where-We-Live-presented-by-THISISPOPBABY/Music-2809048.htm
    http://www.entertainment.ie/show-Dublin/The-Complex/Where-We-Live-The-High-Hopes-Community-Choir/Music-2809132.htm
    http://www.entertainment.ie/show-Dublin/Gaiety-Theatre/Thriller-Live/Music-2813608.htm
    http://www.entertainment.ie/show-Dublin/The-Dolmen-Theatre/Goodnight-Delia/Music-2814046.htm
    http://www.entertainment.ie/show-Dublin/The-Complex/Where-We-Live-The-Mouth-of-a-Shark/Music-2809055.htm
    http://www.entertainment.ie/show-Dublin/Olympia-Theatre/Erasure/Music-2813727.htm
    http://www.entertainment.ie/show-Dublin/The-Academy/The-Wombats/Music-2808590.htm
    http://www.entertainment.ie/show-Dublin/Tivoli-Theatre/Death-From-Above/Music-2813729.htm
    http://www.entertainment.ie/show-Dublin/The-Workmans-Club/Somewhere/Music-2810776.htm
    http://www.entertainment.ie/show-Dublin/The-Grand-Social/Keep-Shelly-In-Athens/Music-2810041.htm
    http://www.entertainment.ie/show-Dublin/Bord-GÃ¡is-Energy-Theatre/TOSCA/Music-2805173.htm
    http://www.entertainment.ie/show-Dublin/Pavilion-Theatre/Oliver/Music-2812174.htm
    http://www.entertainment.ie/show-Dublin/Whelans/Niall-Cash-upstairs/Music-2811447.htm
    http://www.entertainment.ie/show-Dublin/The-Complex/Where-We-Live-presented-by-THISISPOPBABY/Music-2809048.htm
    http://www.entertainment.ie/show-Dublin/The-Complex/Where-We-Live-The-High-Hopes-Community-Choir/Music-2809132.htm
    http://www.entertainment.ie/show-Dublin/Gaiety-Theatre/Thriller-Live/Music-2813608.htm
    http://www.entertainment.ie/show-Dublin/The-Dolmen-Theatre/Goodnight-Delia/Music-2814046.htm
    http://www.entertainment.ie/show-Dublin/The-Complex/Where-We-Live-The-Mouth-of-a-Shark/Music-2809055.htm
    http://www.entertainment.ie/show-Dublin/Bord-GÃ¡is-Energy-Theatre/TOSCA/Music-2805173.htm
    http://www.entertainment.ie/show-Dublin/Pavilion-Theatre/Oliver/Music-2812174.htm
    http://www.entertainment.ie/show-Dublin/Olympia-Theatre/Erasure/Music-2813728.htm
    http://www.entertainment.ie/show-Dublin/Whelans/Jonathan-Wilson/Music-2809834.htm
    http://www.entertainment.ie/show-Dublin/The-Workmans-Club/Karl-Blau/Music-2808684.htm
    http://www.entertainment.ie/show-Dublin/The-Grand-Social/Old-Hannah/Music-2813211.htm
    http://www.entertainment.ie/show-Dublin/Tivoli-Theatre/Sepultura/Music-2811439.htm
    http://www.entertainment.ie/show-Dublin/The-Bowery-Rathmines/Bitch-Falcon-WOLFF-Ape-Rising/Music-2813684.htm
    http://www.entertainment.ie/show-Dublin/Jigsaw/Rita-Braga-Yawning-Chasm-Special-Guests/Music-2813833.htm
    http://www.entertainment.ie/show-Dublin/Mill-Theatre-Dundrum/Garth-Brooks-Live-Tribute-with-Jason-Hughes/Music-2812006.htm
    http://www.entertainment.ie/show-Dublin/The-Bowery-Rathmines/Bitch-Falcon/Music-2812471.htm
    http://www.entertainment.ie/show-Dublin/The-Workmans-Club/AFOG-Show/Music-2814314.htm
    http://www.entertainment.ie/show-Dublin/Whelans/Bicurious-Upstairs/Music-2813856.htm
    http://www.entertainment.ie/show-Dublin/Hapenny-Bridge-Inn/Battle-of-the-Axe/Music-2812482.htm
    http://www.entertainment.ie/show-Dublin/National-Concert-Hall/Philippe-Cassard/Music-2813195.htm
    http://www.entertainment.ie/show-Dublin/Bruxelles/Michael-Buckley-Double-Standard-Time/Music-2812964.htm
    http://www.entertainment.ie/show-Dublin/The-Complex/Where-We-Live-presented-by-THISISPOPBABY/Music-2809048.htm
    http://www.entertainment.ie/show-Dublin/The-Complex/Where-We-Live-The-High-Hopes-Community-Choir/Music-2809132.htm
    http://www.entertainment.ie/show-Dublin/The-Dolmen-Theatre/Goodnight-Delia/Music-2814046.htm
    http://www.entertainment.ie/show-Dublin/Bord-GÃ¡is-Energy-Theatre/TOSCA/Music-2805173.htm
    http://www.entertainment.ie/show-Dublin/Pavilion-Theatre/Oliver/Music-2812174.htm
    http://www.entertainment.ie/show-Dublin/3-Arena/Stereophonics/Music-2805454.htm
    http://www.entertainment.ie/show-Dublin/Olympia-Theatre/The-Stunning/Music-2807607.htm
    http://www.entertainment.ie/show-Dublin/Draiocht/The-Legend-of-Luke-Kelly-with-Chris-Kavanagh/Music-2810310.htm
    http://www.entertainment.ie/show-Dublin/Gaiety-Theatre/Thriller-Live/Music-2813609.htm
    http://www.entertainment.ie/show-Dublin/The-Complex/Home-High-Hopes-Community-Choir/Music-2812860.htm
    http://www.entertainment.ie/show-Dublin/Gaiety-Theatre/Thriller-Live/Music-2813610.htm
    http://www.entertainment.ie/show-Dublin/Arthurs-Pub/The-Dave-Mchugh-Band/Music-2810340.htm
    http://www.entertainment.ie/show-Dublin/The-DC-Music-Club/Musiclee-presents-The-Sick-and-Indigent-Song-Club-in-concert/Music-2813207.htm
    http://www.entertainment.ie/show-Dublin/Annesley-House/Reggae-and-ska-unplugged/Music-2814111.htm
    http://www.entertainment.ie/show-Dublin/Annesley-House/Reggae-and-ska-unplugged/Music-2814112.htm
    http://www.entertainment.ie/show-Dublin/Annesley-House/Reggae-and-ska-unplugged/Music-2814118.htm
    http://www.entertainment.ie/show-Dublin/Bagots-Hutton/Kevin-Rowland-Dexys-Midnight-Runners-Dj-Set/Music-2813248.htm
    http://www.entertainment.ie/show-Dublin/Hang-Dai-Chinese/Eoin-Callanan-at-Hang-Dai/Music-2812213.htm
    http://www.entertainment.ie/show-Dublin/The-Button-Factory/Euphoria-presents-4th-Birthday-Paddys-Eve-with-DJ-ENTRICO-MELONI-ITALY-XTRA-PARTY-ITALY/Music-2814301.htm
    http://www.entertainment.ie/show-Dublin/Whelans/Midnight-Hour-w-Zeros-Ones-upstairs/Music-2814321.htm
    http://www.entertainment.ie/show-Dublin/Hangar/AVA-LONDON-2018/Music-2814334.htm
    http://www.entertainment.ie/show-Dublin/The-Button-Factory/Zooropa-Irelands-1-U2-Tribute/Music-2810105.htm
    http://www.entertainment.ie/show-Dublin/Mill-Theatre-Dundrum/The-Don-Baker-and-Rob-Strong-Band/Music-2812002.htm
    http://www.entertainment.ie/show-Dublin/The-Bowery-Rathmines/TooFools-Live-In-Dublin/Music-2813776.htm
    http://www.entertainment.ie/show-Dublin/The-Bowery-Rathmines/TooFools/Music-2812473.htm
    http://www.entertainment.ie/show-Dublin/Thomas-House/Ghost-Accuser-Album-Launch/Music-2814090.htm
    http://www.entertainment.ie/show-Dublin/Whelans/Brian-Deady/Music-2813435.htm
    http://www.entertainment.ie/show-Dublin/Anseo/Monsieur-Pompiers-Travelling-Freakshow/Music-2814089.htm
    http://www.entertainment.ie/show-Dublin/The-Purty-Kitchen-Dun-Laoghaire/Aaron-Rowe-Debut-Single-Launch/Music-2809820.htm
    http://www.entertainment.ie/show-Dublin/The-Workmans-Club/Fridays-at-Workmans/Music-2811098.htm
    http://www.entertainment.ie/show-Dublin/National-Concert-Hall/Dublins-Theatre-Royal-Remembered/Music-2813196.htm
    http://www.entertainment.ie/show-Dublin/The-Complex/Where-We-Live-presented-by-THISISPOPBABY/Music-2809048.htm
    http://www.entertainment.ie/show-Dublin/The-Complex/Where-We-Live-The-High-Hopes-Community-Choir/Music-2809132.htm
    http://www.entertainment.ie/show-Dublin/The-Dolmen-Theatre/Goodnight-Delia/Music-2814046.htm
    http://www.entertainment.ie/show-Dublin/Bord-GÃ¡is-Energy-Theatre/TOSCA/Music-2805173.htm
    http://www.entertainment.ie/show-Dublin/Pavilion-Theatre/Oliver/Music-2812174.htm
    http://www.entertainment.ie/show-Dublin/Hangar/AVA-LONDON-2018/Music-2814334.htm
    http://www.entertainment.ie/show-Dublin/Vicar-Street/The-Legend-Of-Luke-Kelly/Music-2812230.htm
    http://www.entertainment.ie/show-Dublin/Olympia-Theatre/The-Selecter-and-The-Beat/Music-2798378.htm
    http://www.entertainment.ie/show-Dublin/The-Complex/Where-We-Live-Werkhouse/Music-2809130.htm
    http://www.entertainment.ie/show-Dublin/Gaiety-Theatre/Thriller-Live/Music-2813611.htm
    http://www.entertainment.ie/show-Dublin/Gaiety-Theatre/Thriller-Live/Music-2813612.htm
    http://www.entertainment.ie/show-Dublin/National-Concert-Hall/LÃ¡-FhÃ©ile-PÃ¡draig-The-World-Premiere-of-Sweeney/Music-2813197.htm
    http://www.entertainment.ie/show-Dublin/Arthurs-Pub/The-Real-Skid-Row-50-Years-Party/Music-2810339.htm
    http://www.entertainment.ie/show-Dublin/Bagots-Hutton/Daithi-special-guests/Music-2813510.htm
    http://www.entertainment.ie/show-Dublin/The-Mint-Bar/The-Bratz-Band-in-The-Mint-Bar/Music-2814162.htm
    http://www.entertainment.ie/show-Dublin/Hang-Dai-Chinese/Donal-Dineen-at-Hang-Dai/Music-2812214.htm
    http://www.entertainment.ie/show-Dublin/Hang-Dai-Chinese/Donal-Dineen-at-Hang-Dai/Music-2812215.htm
    http://www.entertainment.ie/show-Dublin/Hang-Dai-Chinese/Donal-Dineen-at-Hang-Dai/Music-2812222.htm
    http://www.entertainment.ie/show-Dublin/The-Bowery-Rathmines/Shogunz-Of-Stank-Mark-GKing-Kong-Company-Gin-N-Juice/Music-2813720.htm
    http://www.entertainment.ie/show-Dublin/The-Purty-Kitchen-Dun-Laoghaire/Complete-Madness/Music-2809821.htm
    http://www.entertainment.ie/show-Dublin/Turks-Head/Irish-Reggae-and-Ska-Special/Music-2814325.htm
    http://www.entertainment.ie/show-Dublin/The-Workmans-Club/Saturdays-at-Workmans/Music-2811068.htm
    http://www.entertainment.ie/show-Dublin/The-Complex/Where-We-Live-presented-by-THISISPOPBABY/Music-2809048.htm
    http://www.entertainment.ie/show-Dublin/The-Complex/Where-We-Live-The-High-Hopes-Community-Choir/Music-2809132.htm
    http://www.entertainment.ie/show-Dublin/Bord-GÃ¡is-Energy-Theatre/TOSCA/Music-2805173.htm
    http://www.entertainment.ie/show-Dublin/Pavilion-Theatre/Oliver/Music-2812174.htm
    http://www.entertainment.ie/show-Dublin/Olympia-Theatre/Aslan/Music-2810054.htm
    http://www.entertainment.ie/show-Dublin/Vicar-Street/Kormac-Equivalent-Exchange/Music-2809794.htm
    http://www.entertainment.ie/show-Dublin/Gaiety-Theatre/Thriller-Live/Music-2813613.htm
    http://www.entertainment.ie/show-Dublin/Gaiety-Theatre/Thriller-Live/Music-2813614.htm
    http://www.entertainment.ie/show-Dublin/Whelans/AILIE-Live-in-Whelans/Music-2813760.htm
    http://www.entertainment.ie/show-Dublin/Whelans/Native-Oak-upstairs/Music-2813772.htm
    http://www.entertainment.ie/show-Dublin/Whelans/Ailie/Music-2812449.htm
    http://www.entertainment.ie/show-Dublin/Bagots-Hutton/The-Catfish-Blues-Orchestra-Live-in-Concert/Music-2813509.htm
    http://www.entertainment.ie/show-Dublin/National-Concert-Hall/Kilfenora-CÃ©ilÃ­-Band-and-Guests/Music-2813198.htm
    http://www.entertainment.ie/show-Dublin/The-Workmans-Club/Karaoke-Club/Music-2810811.htm
    http://www.entertainment.ie/show-Dublin/International-Bar-Dublin/The-Circle-Sessions/Music-2809251.htm
    http://www.entertainment.ie/show-Dublin/Olympia-Theatre/Vance-Joy/Music-2808097.htm
    http://www.entertainment.ie/show-Dublin/The-Workmans-Club/Hippie-Sabotage/Music-2812574.htm
    http://www.entertainment.ie/show-Dublin/Doyles/The-Ruby-Sessions/Music-2810585.htm
    http://www.entertainment.ie/show-Dublin/Tivoli-Theatre/Cannibal-Corpse/Music-2805486.htm
    http://www.entertainment.ie/show-Dublin/National-Concert-Hall/The-Hibernian-Orchestra-Spring-Concert/Music-2813199.htm
    http://www.entertainment.ie/show-Dublin/Olympia-Theatre/Feeder/Music-2812797.htm
    http://www.entertainment.ie/show-Dublin/Bord-GÃ¡is-Energy-Theatre/Joan-Baez/Music-2808505.htm
    http://www.entertainment.ie/show-Dublin/Olympia-Theatre/Feeder/Music-2807589.htm
    http://www.entertainment.ie/show-Dublin/Whelans/The-Niall-McCabe-Band/Music-2814178.htm
    http://www.entertainment.ie/show-Dublin/National-Concert-Hall/Tom-Rogerson/Music-2813082.htm
    http://www.entertainment.ie/show-Dublin/National-Stadium/dodie/Music-2809715.htm
    http://www.entertainment.ie/show-Dublin/Vicar-Street/Bell-X1/Music-2807465.htm
    http://www.entertainment.ie/show-Dublin/The-Workmans-Club/Somewhere/Music-2810777.htm
    http://www.entertainment.ie/show-Dublin/Whelans/Galway-Street-Club-Album-Launch/Music-2813208.htm
    http://www.entertainment.ie/show-Dublin/Whelans/Niall-McCabe-Band/Music-2814097.htm
    http://www.entertainment.ie/show-Dublin/Vicar-Street/Bell-X1/Music-2807465.htm
    http://www.entertainment.ie/show-Dublin/Bord-GÃ¡is-Energy-Theatre/Joan-Baez/Music-2808506.htm
    http://www.entertainment.ie/show-Dublin/Draiocht/Sandy-Kelly-Sings-Patsy-Cline/Music-2810311.htm
    http://www.entertainment.ie/show-Dublin/Whelans/The-Secret-Sister/Music-2808508.htm
    http://www.entertainment.ie/show-Dublin/The-Grand-Social/Garageland-presents-the-Garage-Gigs-in-The-Grand-Social/Music-2814304.htm
    http://www.entertainment.ie/show-Dublin/Whelans/Slow-Riot-upstairs/Music-2814135.htm
    http://www.entertainment.ie/show-Dublin/Bagots-Hutton/Thinner-Lizzy-/Music-2813508.htm
    http://www.entertainment.ie/show-Dublin/Hapenny-Bridge-Inn/Battle-of-the-Axe/Music-2812483.htm
    http://www.entertainment.ie/show-Dublin/Bruxelles/Michael-Buckley-Double-Standard-Time/Music-2812965.htm
    http://www.entertainment.ie/show-Dublin/Vicar-Street/Bell-X1/Music-2807465.htm
    http://www.entertainment.ie/show-Dublin/3-Arena/Nathan-Carter/Music-2813427.htm
    http://www.entertainment.ie/show-Dublin/District-8/Gorgon-City-presents-Kingdom/Music-2809721.htm
    http://www.entertainment.ie/show-Dublin/Olympia-Theatre/All-Tvvins/Music-2807335.htm
    http://www.entertainment.ie/show-Dublin/The-Button-Factory/Little-Hours/Music-2808385.htm
    http://www.entertainment.ie/show-Dublin/The-Grand-Social/Sarah-Darling/Music-2811363.htm
    http://www.entertainment.ie/show-Dublin/The-Academy/Sigrid/Music-2804581.htm
    http://www.entertainment.ie/show-Dublin/Hang-Dai-Chinese/Move-Slow-Records-at-Hang-Dai/Music-2812223.htm
    http://www.entertainment.ie/show-Dublin/Whelans/Midnight-Hour-w-Empathy-Upstairs/Music-2814275.htm
    http://www.entertainment.ie/show-Dublin/Marlay-House/Marlay-House-Traditional-Irish-Music-Nights/Music-2814188.htm
    http://www.entertainment.ie/show-Dublin/Bradys-Bar-Terenure/The-Bradys-Sessions/Music-2814313.htm
    http://www.entertainment.ie/show-Dublin/The-Purty-Kitchen-Dun-Laoghaire/Rub-a-Dub-Hi-Fi/Music-2809822.htm
    http://www.entertainment.ie/show-Dublin/The-Workmans-Club/Fridays-at-Workmans/Music-2811099.htm
    http://www.entertainment.ie/show-Dublin/National-Concert-Hall/RTÃ‰-NSO-Pogostkina-Plays-Khachaturian/Music-2813202.htm
    http://www.entertainment.ie/show-Dublin/National-Concert-Hall/Songs-of-a-Gypsy-Life/Music-2813200.htm
    http://www.entertainment.ie/show-Dublin/Vicar-Street/Bell-X1/Music-2807465.htm
    http://www.entertainment.ie/show-Dublin/3-Arena/Paloma-Faith/Music-2805332.htm
    http://www.entertainment.ie/show-Dublin/The-Academy/Greywind/Music-2810393.htm
    http://www.entertainment.ie/show-Dublin/Whelans/Barq/Music-2813568.htm
    http://www.entertainment.ie/show-Dublin/Hang-Dai-Chinese/Andy-Flynn-Rhythm-and-Booze-Limerick-at-Hang-Dai/Music-2812216.htm
    http://www.entertainment.ie/show-Dublin/Church-of-the-Sacred-Heart/Messiah-An-Easter-performance/Music-2813794.htm
    http://www.entertainment.ie/show-Dublin/The-Workmans-Club/Bone-Machine-Play-the-Music-of-Tom-Waits/Music-2813697.htm
    http://www.entertainment.ie/show-Dublin/The-Purty-Kitchen-Dun-Laoghaire/Cash-Returns-Live-in-Concert/Music-2809823.htm
    http://www.entertainment.ie/show-Dublin/The-Workmans-Club/Saturdays-at-Workmans/Music-2811069.htm
    http://www.entertainment.ie/show-Dublin/Vicar-Street/Bell-X1/Music-2807465.htm
    http://www.entertainment.ie/show-Dublin/Whelans/Lee-Scratch-Perry/Music-2808496.htm
    http://www.entertainment.ie/show-Dublin/3-Arena/Flight-of-the-Conchords/Music-2807275.htm
    http://www.entertainment.ie/show-Dublin/Civic-Theatre/Jerusalem-Tomorrow/Music-2811221.htm
    http://www.entertainment.ie/show-Dublin/The-Workmans-Club/Dream-Wife/Music-2808498.htm
    http://www.entertainment.ie/show-Dublin/Toners-Victorian-Bar/Strand-play-the-Alternative-Sunday-Social-Club/Music-2813914.htm
    http://www.entertainment.ie/show-Dublin/Olympia-Theatre/Anne-Marie/Music-2805847.htm
    http://www.entertainment.ie/show-Dublin/The-Workmans-Club/Karaoke-Club/Music-2810812.htm
    http://www.entertainment.ie/show-Dublin/National-Concert-Hall/Complete-Beethoven-Quartet/Music-2813203.htm
    


```python
for info in entertainment.get_info():
    print info
```

    (u'The Gloaming', u'National Concert Hall', '\xe2\x82\xac40', u"Since their formation in 2011, The Gloaming have sold out an unprecedented 17 consecutive concerts at Ireland's National Concert Hall and celebrated a Number 1 album in the Irish Charts.", '2018-3-10 :', '')
    (u'Where We Live presented by THISISPOPBABY', u'The Complex', '', u'WHERE WE LIVE is a ferocious response to the theme of home. It\u2019s a kaleidoscope of stories about what it feels like to live in Dublin and Ireland today, told by some of the best storytellers on the island.', '2018-3-06 :', '')
    (u'Waka Flocka Flame', u'The Sugar Club', '\xe2\x82\xac22', u'Global icon and hip-hop artist is back and better than ever with his new album Flockaveli II', '2018-3-12 :', '')
    (u'Where We Live -  The High Hopes Community Choir', u'The Complex', '\xe2\x82\xac10', u'Building on the success of their choral performances since 2014, the members now embark on a new journey with The Complex and its artists to present an evening of music, theatre, spoken word, visual art and poetry,', '2018-3-07 20:00', '')
    (u'Thriller Live', u'Gaiety Theatre', '\xe2\x82\xac36/\xe2\x82\xac46', u'You will experience over two hours of non-stop hits from pop to rock, soul to disco as the cast pay homage to Jackson\u2019s legendary live performances and innovative dance moves executed with flair, precision and passion', '2018-3-12 19:30', '')
    (u'The Circle Sessions', u'International Bar, Dublin', '', u'A night of Spoken Word Poetry, Storytelling, Music and Performance in the International Bar, Exchequer Street, Dublin.', '2018-3-12 20:00', '')
    (u'Rejjie Snow', u'Olympia Theatre', '\xe2\x82\xac22', u'His biggest UK and European headline tour to date.', '2018-3-12 :', '')
    (u'Goodnight Delia', u'The Dolmen Theatre', '\xe2\x82\xac15', u"A tender, funny & moving homage to Irish singer Delia Murphy, her life as an ambassador's wife, her stand against the Nazis in WWII & her remarkable career as Ireland's first international popstar.", '2018-3-05 20:00', '')
    


    ---------------------------------------------------------------------------

    KeyboardInterrupt                         Traceback (most recent call last)

    <ipython-input-40-ac89b1711de8> in <module>()
    ----> 1 for info in entertainment.get_info():
          2     print info
    

    C:\Users\Patrick\Desktop\frick\2018-CA326-dbaranauskas-dublinevents\code\scrapers\entertainment.pyc in get_info()
         57         headers = {
         58             'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36'}
    ---> 59         page_raw = requests.get(link, headers=headers)
         60         page_html = Soup(page_raw.content, 'html5lib')
         61         block = page_html.find("div", {"class": "listing maincol"})
    

    C:\Python27\lib\site-packages\requests\api.pyc in get(url, params, **kwargs)
         70 
         71     kwargs.setdefault('allow_redirects', True)
    ---> 72     return request('get', url, params=params, **kwargs)
         73 
         74 
    

    C:\Python27\lib\site-packages\requests\api.pyc in request(method, url, **kwargs)
         56     # cases, and look like a memory leak in others.
         57     with sessions.Session() as session:
    ---> 58         return session.request(method=method, url=url, **kwargs)
         59 
         60 
    

    C:\Python27\lib\site-packages\requests\sessions.pyc in request(self, method, url, params, data, headers, cookies, files, auth, timeout, allow_redirects, proxies, hooks, stream, verify, cert, json)
        506         }
        507         send_kwargs.update(settings)
    --> 508         resp = self.send(prep, **send_kwargs)
        509 
        510         return resp
    

    C:\Python27\lib\site-packages\requests\sessions.pyc in send(self, request, **kwargs)
        638 
        639         # Resolve redirects if allowed.
    --> 640         history = [resp for resp in gen] if allow_redirects else []
        641 
        642         # Shuffle things around if there's history.
    

    C:\Python27\lib\site-packages\requests\sessions.pyc in resolve_redirects(self, resp, req, stream, timeout, verify, cert, proxies, yield_requests, **adapter_kwargs)
        216                     proxies=proxies,
        217                     allow_redirects=False,
    --> 218                     **adapter_kwargs
        219                 )
        220 
    

    C:\Python27\lib\site-packages\requests\sessions.pyc in send(self, request, **kwargs)
        616 
        617         # Send the request
    --> 618         r = adapter.send(request, **kwargs)
        619 
        620         # Total elapsed time of the request (approximately)
    

    C:\Python27\lib\site-packages\requests\adapters.pyc in send(self, request, stream, timeout, verify, cert, proxies)
        438                     decode_content=False,
        439                     retries=self.max_retries,
    --> 440                     timeout=timeout
        441                 )
        442 
    

    C:\Python27\lib\site-packages\urllib3\connectionpool.pyc in urlopen(self, method, url, body, headers, retries, redirect, assert_same_host, timeout, pool_timeout, release_conn, chunked, body_pos, **response_kw)
        599                                                   timeout=timeout_obj,
        600                                                   body=body, headers=headers,
    --> 601                                                   chunked=chunked)
        602 
        603             # If we're going to release the connection in ``finally:``, then
    

    C:\Python27\lib\site-packages\urllib3\connectionpool.pyc in _make_request(self, conn, method, url, timeout, chunked, **httplib_request_kw)
        378         try:
        379             try:  # Python 2.7, use buffering of HTTP responses
    --> 380                 httplib_response = conn.getresponse(buffering=True)
        381             except TypeError:  # Python 2.6 and older, Python 3
        382                 try:
    

    C:\Python27\lib\httplib.pyc in getresponse(self, buffering)
       1119 
       1120         try:
    -> 1121             response.begin()
       1122             assert response.will_close != _UNKNOWN
       1123             self.__state = _CS_IDLE
    

    C:\Python27\lib\httplib.pyc in begin(self)
        436         # read until we get a non-100 response
        437         while True:
    --> 438             version, status, reason = self._read_status()
        439             if status != CONTINUE:
        440                 break
    

    C:\Python27\lib\httplib.pyc in _read_status(self)
        392     def _read_status(self):
        393         # Initialize with Simple-Response defaults
    --> 394         line = self.fp.readline(_MAXLINE + 1)
        395         if len(line) > _MAXLINE:
        396             raise LineTooLong("header line")
    

    C:\Python27\lib\socket.pyc in readline(self, size)
        478             while True:
        479                 try:
    --> 480                     data = self._sock.recv(self._rbufsize)
        481                 except error, e:
        482                     if e.args[0] == EINTR:
    

    KeyboardInterrupt: 



```python
with db:
    db.add_to_db_event((u'Waka Flocka Flame', u'The Sugar Club', '\xe2\x82\xac22', u'Global icon and hip-hop artist is back and better than ever with his new album Flockaveli II', '2018-3-12 :', ''))
```

    Connected to database
    Finished with database
    


```python
print db.output
```

    Error: 1062 (23000): Duplicate entry 'Waka Flocka Flame-The Sugar Club-2018-03-12 00:00:00' for key 'PRIMARY'
    
    
