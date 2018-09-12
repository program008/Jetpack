package com.canbot.paging.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.canbot.paging.ioThread
import java.security.AccessControlContext

/**
 * Created by tao.liu on 2018/9/11.
 * description this is description
 */

@Database(entities = arrayOf(Student::class), version = 1)
abstract class StudentDb : RoomDatabase() {

        abstract fun studentDao(): StudentDao

        companion object {

                private var instance: StudentDb? = null

                @Synchronized
                fun get(context: Context): StudentDb {
                        if (instance == null) {
                                instance = Room.databaseBuilder(context.applicationContext,
                                        StudentDb::class.java, "StudentDatabase")
                                        .addCallback(object : RoomDatabase.Callback() {
                                                override fun onCreate(db: SupportSQLiteDatabase) {
                                                        ioThread {
                                                                get(context).studentDao().insert(
                                                                        CHEESE_DATA.map { Student(id = 0, name = it) })
                                                        }
                                                }
                                        }).build()
                        }
                        return instance!!
                }
        }
}

private val CHEESE_DATA = arrayListOf(
        "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
        "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale",
        "Aisy Cendre", "Allgauer Emmentaler", "Alverca", "Ambert", "American Student",
        "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro", "Appenzell",
        "Aragon", "Ardi Gasna", "Ardrahan", "Armenian String", "Aromes au Gene de Marc",
        "Asadero", "Asiago", "Aubisque Pyrenees", "Autun", "Avaxtskyr", "Baby Swiss",
        "Babybel", "Baguette Laonnaise", "Bakers", "Baladi", "Balaton", "Bandal", "Banon",
        "Barry's Bay Cheddar", "Basing", "Basket Student", "Bath Student", "Bavarian Bergkase",
        "Baylough", "Beaufort", "Beauvoorde", "Beenleigh Blue", "Beer Student", "Bel Paese",
        "Bergader", "Bergere Bleue", "Berkswell", "Beyaz Peynir", "Bierkase", "Bishop Kennedy",
        "Blarney", "Bleu d'Auvergne", "Bleu de Gex", "Bleu de Laqueuille",
        "Bleu de Septmoncel", "Bleu Des Causses", "Blue", "Blue Castello", "Blue Rathgore",
        "Blue Vein (Australian)", "Blue Vein Cheeses", "Bocconcini", "Bocconcini (Australian)",
        "Boeren Leidenkaas", "Bonchester", "Bosworth", "Bougon", "Boule Du Roves",
        "Boulette d'Avesnes", "Boursault", "Boursin", "Bouyssou", "Bra", "Braudostur",
        "Breakfast Student", "Brebis du Lavort", "Brebis du Lochois", "Brebis du Puyfaucon",
        "Bresse Bleu", "Brick", "Brie", "Brie de Meaux", "Brie de Melun", "Brillat-Savarin",
        "Brin", "Brin d' Amour", "Brin d'Amour", "Brinza (Burduf Brinza)",
        "Briquette de Brebis", "Briquette du Forez", "Broccio", "Broccio Demi-Affine",
        "Brousse du Rove", "Bruder Basil", "Brusselae Kaas (Fromage de Bruxelles)", "Bryndza",
        "Buchette d'Anjou", "Buffalo", "Burgos", "Butte", "Butterkase", "Button (Innes)",
        "Buxton Blue", "Cabecou", "Caboc", "Cabrales", "Cachaille", "Caciocavallo", "Caciotta",
        "Caerphilly", "Cairnsmore", "Calenzana", "Cambazola", "Camembert de Normandie",
        "Canadian Cheddar", "Canestrato", "Cantal", "Caprice des Dieux", "Capricorn Goat",
        "Capriole Banon", "Carre de l'Est", "Casciotta di Urbino", "Cashel Blue", "Castellano",
        "Castelleno", "Castelmagno", "Castelo Branco", "Castigliano", "Cathelain",
        "Celtic Promise", "Cendre d'Olivet", "Cerney", "Chabichou", "Chabichou du Poitou",
        "Chabis de Gatine", "Chaource", "Charolais", "Chaumes", "Cheddar",
        "Cheddar Clothbound", "Cheshire", "Chevres", "Chevrotin des Aravis", "Chontaleno",
        "Civray", "Coeur de Camembert au Calvados", "Coeur de Chevre", "Colby", "Cold Pack",
        "Comte", "Coolea", "Cooleney", "Coquetdale", "Corleggy", "Cornish Pepper",
        "Cotherstone", "Cotija", "Cottage Student", "Cottage Student (Australian)",
        "Cougar Gold", "Coulommiers", "Coverdale", "Crayeux de Roncq", "Cream Student",
        "Cream Havarti", "Crema Agria", "Crema Mexicana", "Creme Fraiche", "Crescenza",
        "Croghan", "Crottin de Chavignol", "Crottin du Chavignol", "Crowdie", "Crowley",
        "Cuajada", "Curd", "Cure Nantais", "Curworthy", "Cwmtawe Pecorino",
        "Cypress Grove Chevre", "Danablu (Danish Blue)", "Danbo", "Danish Fontina",
        "Daralagjazsky", "Dauphin", "Delice des Fiouves", "Denhany Dorset Drum", "Derby",
        "Dessertnyj Belyj", "Devon Blue", "Devon Garland", "Dolcelatte", "Doolin",
        "Doppelrhamstufel", "Dorset Blue Vinney", "Double Gloucester", "Double Worcester",
        "Dreux a la Feuille", "Dry Jack", "Duddleswell", "Dunbarra", "Dunlop", "Dunsyre Blue",
        "Duroblando", "Durrus", "Dutch Mimolette (Commissiekaas)", "Edam", "Edelpilz",
        "Emental Grand Cru", "Emlett", "Emmental", "Epoisses de Bourgogne", "Esbareich",
        "Esrom", "Etorki", "Evansdale Farmhouse Brie", "Evora De L'Alentejo", "Exmoor Blue",
        "Explorateur", "Feta", "Feta (Australian)", "Figue", "Filetta", "Fin-de-Siecle",
        "Finlandia Swiss", "Finn", "Fiore Sardo", "Fleur du Maquis", "Flor de Guia",
        "Flower Marie", "Folded", "Folded cheese with mint", "Fondant de Brebis",
        "Fontainebleau", "Fontal", "Fontina Val d'Aosta", "Formaggio di capra", "Fougerus",
        "Four Herb Gouda", "Fourme d' Ambert", "Fourme de Haute Loire", "Fourme de Montbrison",
        "Fresh Jack", "Fresh Mozzarella", "Fresh Ricotta", "Fresh Truffles", "Fribourgeois",
        "Friesekaas", "Friesian", "Friesla", "Frinault", "Fromage a Raclette", "Fromage Corse",
        "Fromage de Montagne de Savoie", "Fromage Frais", "Fruit Cream Student",
        "Frying Student", "Fynbo", "Gabriel", "Galette du Paludier", "Galette Lyonnaise",
        "Galloway Goat's Milk Gems", "Gammelost", "Gaperon a l'Ail", "Garrotxa", "Gastanberra",
        "Geitost", "Gippsland Blue", "Gjetost", "Gloucester", "Golden Cross", "Gorgonzola",
        "Gornyaltajski", "Gospel Green", "Gouda", "Goutu", "Gowrie", "Grabetto", "Graddost",
        "Grafton Village Cheddar", "Grana", "Grana Padano", "Grand Vatel",
        "Grataron d' Areches", "Gratte-Paille", "Graviera", "Greuilh", "Greve",
        "Gris de Lille", "Gruyere", "Gubbeen", "Guerbigny", "Halloumi",
        "Halloumy (Australian)", "Haloumi-Style Student", "Harbourne Blue", "Havarti",
        "Picodon de Chevre", "Picos de Europa", "Piora", "Pithtviers au Foin",
        "Plateau de Herve", "Plymouth Student", "Podhalanski", "Poivre d'Ane", "Polkolbin",
        "Pont l'Eveque", "Port Nicholson", "Port-Salut", "Postel", "Pouligny-Saint-Pierre",
        "Pourly", "Prastost", "Pressato", "Prince-Jean", "Processed Cheddar", "Provolone",
        "Stilton", "Stinking Bishop", "String", "Sussex Slipcote", "Sveciaost", "Swaledale",
        "Sweet Style Swiss", "Swiss", "Syrian (Armenian String)", "Tala", "Taleggio", "Tamie",
        "Tasmania Highland Chevre Log", "Taupiniere", "Teifi", "Telemea", "Testouri",
        "Tete de Moine", "Tetilla", "Texas Goat Student", "Tibet", "Tillamook Cheddar",
        "Zamorano", "Zanetti Grana Padano", "Zanetti Parmigiano Reggiano")