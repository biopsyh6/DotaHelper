class Hero(_name: String, _health: Int, _mana: Int, _dexterity: Double, _intelligence: Double,
    _strength: Double, _damage: Int, _armor: Double, _speed: Int, _attackRange: Int,
           _attackSpeed: Int, _skills: String) {
    private val name: String
    private val health: Int
    private val mana: Int
    private val dexterity: Double
    private val intelligence: Double
    private val strength: Double
    private val damage: Int
    private val armor: Double
    private val speed: Int
    private val attackRange: Int
    private val attackSpeed: Int
    private val skills: String
    init {
        name = _name
        health = _health
        mana = _mana
        dexterity = _dexterity
        intelligence = _intelligence
        strength = _strength
        damage = _damage
        armor = _armor
        speed = _speed
        attackRange = _attackRange
        attackSpeed = _attackSpeed
        skills = _skills
    }
}