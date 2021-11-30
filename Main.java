import java.util.ArrayList;
import java.util.Objects;

class Membru {
    private String nume;
    private String prenume;
    private int varsta;
    private double salariu;
    private int experienta;

    public Membru(String nume, String prenume, int varsta, double salariu, int experienta) {
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
        this.salariu = salariu;
        this.experienta = experienta;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public int getExperienta() {
        return experienta;
    }

    public int getVarsta() {
        return varsta;
    }

    public double getSalariu() {
        return salariu;
    }

    public String toString() {
        return this.nume + " " + this.prenume;
    }

    @Override
    public boolean equals(Object membru) {
        if(membru == this) {
            return true;
        }
        if (membru instanceof Membru) {
            return Objects.equals(this.nume, ((Membru)membru).nume) && 
            Objects.equals(this.prenume, ((Membru)membru).prenume) && 
            Integer.compare(this.experienta, ((Membru)membru).experienta) == 0 && 
            Integer.compare(this.varsta, ((Membru)membru).varsta) == 0 && 
            Double.compare(this.salariu, ((Membru)membru).salariu) == 0;
        } 
        return false;
    }
}

abstract class Echipa {
    private String nume;
    private Membru lider;
    private int maxi;
    protected ArrayList<Membru> membri = new ArrayList<Membru>();

    protected Echipa(String nume, int maxi) {
        this.nume = nume;
        this.maxi = maxi;
    }

    public void setNume (String nume, Membru m) {
        if(this.lider.equals(m)) {
            this.nume = nume;
        } else {
            System.out.println("Unauthorized action!");
        }
    }

    public void setMaxi (int maxi, Membru m) {
        if(this.lider.equals(m)) {
            this.maxi = maxi;
        } else {
            System.out.println("Unauthorized action!");
        }
    }

    public boolean addMember(Membru member) {
        if (this.membri.size() < this.maxi) {
            this.membri.add(member);
            return true;
        }

        return false;
    }

    public boolean setLeader(Membru liderNou) {
        if (liderNou.getExperienta() >= 5) {
            this.lider = liderNou;
            return true;
        }

        return false;
    }

    public Membru removeMember(Membru member) {
        for(Membru i : membri) {
            if (i.equals(member)) {
                this.membri.remove(i);
                return member;
            }
        }

        return null;
    }

    public String toString() {
        String tmp = "Lider echipa: " + this.lider.toString() + "\nMembri:\n";
        int nr = 1;
        for(Membru i : membri) {
            tmp = tmp + "\t" + nr + ". " + i.toString() + "\n";
            nr++;
        }

        return tmp;
    }

    public Membru getLider() {
        return this.lider;
    }

    public abstract double getCost();
}

class DevTeam extends Echipa {

    public DevTeam(String nume, int maxi) {
        super(nume, maxi);
    }

    public double getCost() {
        double tmp = 2500 + this.getLider().getExperienta() * 250;
        for(Membru i : membri) {
            double percent;
            if(i.getExperienta() < 2) {
                percent = 1500 * 0 / 100;
            } else {
                if(i.getExperienta() >= 5) {
                    percent = 1500 * 50 / 100;
                } else {
                    percent = 1500 * 25 / 100;
                }
            }
            tmp += 1500 + percent;
        }

        return tmp;
    }
}

class HR extends Echipa {

    public HR(String nume, int maxi) {
        super(nume, maxi);
    }

    public double getCost() {
        double tmp = 1300 + 300 * this.getLider().getExperienta();
        for(Membru i : membri) {
            double percent;
            if(i.getExperienta() < 2) {
                percent = 1000 * 0 / 100;
            } else {
                if(i.getExperienta() >= 5) {
                    percent = 1000 * 50 / 100;
                } else {
                    percent = 1000 * 25 / 100;
                }
            }
            tmp += 1000 + percent;
        }

        return tmp;
    }
}

class Main {
    public static void main(String[] args) {

        // ----------------- DEV ---------------------------
        Echipa dev = new DevTeam("DEV TEAM", 15);

        dev.setLeader(new Membru("Popescu", "Ion", 30, 8500, 3));
        dev.setLeader(new Membru("Ionescu", "Maria", 40, 6500, 8));

        dev.addMember(new Membru("Popa", "Marin", 38, 2500, 2));
        dev.addMember(new Membru("Pop", "Tudor", 23, 2500, 1));
        dev.addMember(new Membru("Stan", "Ana", 37, 5400, 6));
        dev.addMember(new Membru("Stanciu", "Mihaela", 36, 9000, 8));
        dev.addMember(new Membru("Dumitrescu", "Florin", 58, 5500, 6));

        System.out.println("Costuri totale: " + dev.getCost());
        System.out.println(dev);

        Membru m1 = new Membru("Stanciu", "Mihaela", 36, 9000, 8);
        System.out.println(dev.removeMember(m1));

        System.out.println("Dupa stergere:");
        System.out.println(dev);
        System.out.println("Costuri totale: " + dev.getCost());

        System.out.println("\n\n");

        // ------------------ HR ---------------------------
        Echipa hr = new HR("HR TEAM", 40);

        hr.setLeader(new Membru("Iovanovici", "Mara", 40, 6500, 3));
        hr.setLeader(new Membru("Popovici", "Ionela", 30, 8500, 8));

        hr.addMember(new Membru("Dima", "Elena", 27, 3500, 4));
        hr.addMember(new Membru("Gheorghiu", "Adrian", 49, 2400, 2));
        hr.addMember(new Membru("Ionita", " Andrei", 58, 9300, 12));
        hr.addMember(new Membru("Dobre", "Alexandra", 21, 6300, 3));
        hr.addMember(new Membru("Barbu", "Mihai", 38, 3200, 5));

        System.out.println("Costuri totale: " + hr.getCost());
        System.out.println(hr);

        Membru m2 = new Membru("Gheorghiu", "Adrian", 49, 2400, 2);
        System.out.println(hr.removeMember(m2));

        System.out.println("Dupa stergere:");
        System.out.println(hr);
        System.out.println("Costuri totale: " + hr.getCost());
    }
}