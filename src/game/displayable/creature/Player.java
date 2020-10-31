package game.displayable.creature;

import game.displayable.item.Item;

public class Player extends Creature /*implements Runnable, inputObserver*/ {
    private String name;
    private int room;
    private int serial;

    private Item sword;
    private Item armor;

    public void setName(String _name) { this.name = _name; }

    public String getName() { return this.name; }

    public void setID(int _room, int _serial) {
        this.room = _room;
        this.serial = _serial;
    }

    public int getRoom() { return this.room; }

    public void setWeapon(Item _sword) {
        sword = _sword;
    }

    public void setArmor(Item _armor) {
        armor = _armor;
    }

//    private static final int DEBUG = 1;
//    private static final String CLASSID ="game.displayble.creature.Player";
//    private static final int MAX_PACK_SIZE = 5;
//    private static Queue<Character> inputQueue = null;
//
//    private Item weapon = null;
//    private Item armor = null;
//    private int moveCount =0;

//    public Player(String name){
//        super();
//        type = '@';
//        inputQueue = new ConcurrentLinkedQueue<>();
//        this.name = name;
//    }
//    @Override
//    public void initializeDisplayofSelf(){
//        if(DEBUG > 1){
//            System.out.println(CLASSID + ".initializeDisplayOfSelf type "+ type);
//        }
//        System.out.println(CLASSID + ".initializeDisplayOfSelf posX" + posX + ",poxY" + posY+", type: "+ type+", hp"+hp);
//        super.initializeDisplayOfSelf();
//        ObjectDisplayGrid.writeUp(hp);
//    }
//
//    @Override
//    public String toString(){
//        return super.toString();
//    }
//    public void setArmor(Armor armor){
//        this.armor = armor;
//    }
//    public void setWeapon(Sword sword){
//        weapon = sword;
//    }
//
//    @Override
//    public void performBeingHitActions(Displayble attacker){
//        if(DEBUG > 1){
//            System.out.println(CLASSID + ".performBeingHitActions intial hp: "+ hp);
//        }
//        for(CreatureAction action: hitActions){
//            action.perdormAction();
//        }
//        int armorProtection = 0;
//        if(armor != null){
//            armorProtection = armor.getInflictedDamage();
//        }
//        int damage = attacker.getInflictedDamage() - armorProtection;
//        setHP(hp - ((damage>0)? damage : -damage));
//        if(DEBUG > 1){
//            System.out.println(CLASSID + ".performBeingHitActions");
//            System.out.println("armor mitigation: "+armor.getInflictedDamage());
//            System.out.println("encroacher damage: "+attacker+getInflictedDamage());
//            System.out.println("damage incurred: "+damage);
//            System.out.println("hp after hit: "+hp);
//
//        }
//        if(hp<=0){
//            performDeathActions(attacker);
//        }
//    }
//    @Override
//    public void observeUpdate(char ch){
//        if(DEBUG >0){
//            System.out.println(CLASSID + ".observeUpdate receiving character "+ ch);
//        }
//        inputQueue.add(ch);
//    }
//
//    @Override
//    protected void performMoveActions(int x,int y,Displayble moveToObject){
//        moveCount = ++moveCount % hpMoves;
//        if(moveCount == 0){
//            setHp(hp++);
//            ObjectDisplayGrid.writeHp(hp);
//        }
//        moveToObject.performBeingHitActions(this);
//    }
//
//    @Override
//    public int getInflictedDamage(){
//        int damage = getRandom(maxHit);
//        if(weapon != null){
//            damage += weapon.getInflictedDamage();
//
//        }
//        return damage;
//    }
//
//    @Override
//    protected void performDeathActions(Displayble attacker){
//        ObjectDisplaGrid.showDisplayableGridPoint(this.getPosX(),this.getPosY(),0);
//        for(CreatureAction action : deathActions){
//            action.performAction();
//        }
//        ObjectDisplayGrid.writeHp(hp);
//    }
//
//    public Item getArmor() {
//        return armor;
//    }
//
//    public Item getWeapon() {
//        return weapon;
//    }
//
//    public boolean processInput(){
//        char ch;
//
//        if(inputQueue.peak() == null){
//            return fals;
//        }else{
//            ch = inputQueue.poll();
//            if(DEBUG >1){
//                System.out.println(CLASSID + ".processInput peek is "+ch);
//            }
//        }
//
//        System.out.println("recieved input is "+ ch);
//
//        boolean retValue = false;
//        switch(ch){
//            case 'M': // help with more info
//                getNextInput();
//                break;
//            case 'E':
//                getNextInput();
//                break;
//            case 'h':
//                retValue = moveLeft();
//                break;
//            case 'l':
//                retValue = moveRight();
//                break;
//            case 'k':
//                retValue = moveUp();
//                break;
//            case 'j':
//                retValue = moveDown();
//                break;
//            case 'i':
//                retValue = showInventory();
//                break;
//            case 'c':
//                takeOffArmor();
//                break;
//            case 'd':
//                retValue = drop(getNextInput());
//                break;
//            case 'p':
//                System.out.println(CLASSID + ".processInput in 'p");
//                retValue = pickup();
//                break;
//            case 'r':
//                retValue = readItem(getMaxInput());
//                break;
//            case 'w':
//                retValue = wearItem(getNextInput());
//                break;
//            case '0':
//            case '1':
//            case '3':
//            case '4':
//            case '5':
//            case '6':
//            case '7':
//            case '8':
//            case '9':
//                break;
//            default:
//                if(DEBUG>0){
//                    System.out.println(CLASSID + ".processInput unknown char");
//                }
//        }
//
//    }
}
