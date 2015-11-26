package items;

public enum Producers {
    Bridgestone(0),
    Continental(1),
    Barum(2),
    Gislaved(3),
    Cooper(4),
    Federal(5),
    Firestone(6),
    Fulda(7),
    Goodrich(8),
    Goodyear(9),
    Dunlop(10),
    Hankook(11),
    Kumho(12),
    Lassa(13),
    Matador(14),
    Michelin(15),
    Nokian(16),
    Pirelli(17),
    Toyo(18),
    Yokohama(19);

    private int id;

    private  Producers(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
}
