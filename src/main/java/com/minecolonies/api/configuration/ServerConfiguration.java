package com.minecolonies.api.configuration;

import com.minecolonies.api.util.constant.NameConstants;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.minecolonies.api.util.constant.Constants.*;

/**
 * Mod server configuration. Loaded serverside, synced on connection.
 */
@Config(name = "server_config")
public class ServerConfiguration extends AbstractConfiguration {

	@ConfigEntry.Category("names")
	@ConfigEntry.Gui.TransitiveObject
	public static final Names names = new Names();

	@ConfigEntry.Category("gameplay")
	@ConfigEntry.Gui.TransitiveObject
	public static final Gameplay gameplay = new Gameplay();

	@ConfigEntry.Category("command")
	@ConfigEntry.Gui.TransitiveObject
	public static final Command command = new Command();

	@ConfigEntry.Category("claims")
	@ConfigEntry.Gui.TransitiveObject
	public static final Claims claims = new Claims();

	@ConfigEntry.Category("combat")
	@ConfigEntry.Gui.TransitiveObject
	public static final Combat combat = new Combat();

	@ConfigEntry.Category("permissions")
	@ConfigEntry.Gui.TransitiveObject
	public static final Permissions permissions = new Permissions();

	@ConfigEntry.Category("compatibility")
	@ConfigEntry.Gui.TransitiveObject
	public static final Compatibility compatibility = new Compatibility();

	@ConfigEntry.Category("pathfinding")
	@ConfigEntry.Gui.TransitiveObject
	public static final Pathfinding pathfinding = new Pathfinding();

	@ConfigEntry.Category("requestSystem")
	@ConfigEntry.Gui.TransitiveObject
	public static final RequestSystem requestSystem = new RequestSystem();

	@ConfigEntry.Category("research")
	@ConfigEntry.Gui.TransitiveObject
	public static final Research research = new Research();

	public static class Names {
		public final boolean useMiddleInitial = true;
		public final boolean useEasternNameOrder = false;
		public final List<? extends String> maleFirstNames = Arrays.asList(NameConstants.maleFirstNames);
		public final List<? extends String> femaleFirstNames = Arrays.asList(NameConstants.femaleFirstNames);
		public final List<? extends String> lastNames = Arrays.asList(NameConstants.lastNames);
	}

	public static class Gameplay {
		@ConfigEntry.BoundedDiscrete(min = 1, max = 10)
		public static final int initialCitizenAmount = 4;
		public static final boolean builderPlaceConstructionTape = true;
		public static final boolean allowInfiniteSupplyChests = false;
		public static final boolean allowInfiniteColonies = false;
		public static final boolean allowOtherDimColonies = false;
		@ConfigEntry.BoundedDiscrete(min = CITIZEN_RESPAWN_INTERVAL_MIN, max = CITIZEN_RESPAWN_INTERVAL_MAX)
		public static final int citizenRespawnInterval = 60;
		@ConfigEntry.BoundedDiscrete(min = 4, max = 500)
		public static final int maxCitizenPerColony = 150;
		public static final boolean builderInfiniteResources = false;
		public static final boolean limitToOneWareHousePerColony = true;
		@ConfigEntry.BoundedDiscrete(min = 1, max = 500)
		public static final int builderBuildBlockDelay = 15;
		@ConfigEntry.BoundedDiscrete(min = 1, max = 10000)
		public static final int blockMiningDelayModifier = 500;
		public static final boolean enableInDevelopmentFeatures = false;
		public static final boolean alwaysRenderNameTag = true;
		@ConfigEntry.BoundedDiscrete(min = 1, max = 100)
		public static final double growthModifier = 1;
		public static final boolean workersAlwaysWorkInRain = false;
		public static final boolean sendEnteringLeavingMessages = true;
		@ConfigEntry.BoundedDiscrete(min = -1, max = 1)
		public final int allowGlobalNameChanges = 1;
		public static final boolean holidayFeatures = true;
		@ConfigEntry.BoundedDiscrete(min = 1, max = 100)
		public static final int updateRate = 1;
		@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
		public static final int dirtFromCompost = 1;
		@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
		public static final int luckyBlockChance = 1;
		public static final boolean fixOrphanedChunks = false;
		public static final boolean restrictBuilderUnderground;
		public static final double fisherSpongeChance;
		public static final double fisherPrismarineChance;
		public static final int minThLevelToTeleport;
		public static final boolean suggestBuildToolPlacement;
		public static final double foodModifier;
		public static final int diseaseModifier;
		public static final boolean forceLoadColony;
		public static final int badVisitorsChance;
	}

	public static class Command {
		public final boolean canPlayerUseRTPCommand = true;
		public final boolean canPlayerUseColonyTPCommand = false;
		public final boolean canPlayerUseAllyTHTeleport = true;
		public final boolean canPlayerUseHomeTPCommand = true;
		public final boolean canPlayerUseShowColonyInfoCommand = true;
		public final boolean canPlayerUseKillCitizensCommand = true;
		public final boolean canPlayerUseAddOfficerCommand = true;
		public final boolean canPlayerUseDeleteColonyCommand = true;
		@ConfigEntry.BoundedDiscrete(min = 1, max = 20)
		public final int numberOfAttemptsForSafeTP = 4;
	}

	public static class Claims {
		@ConfigEntry.BoundedDiscrete(min = 1, max = 50)
		public final int maxColonySize = 20;
		@ConfigEntry.BoundedDiscrete(min = 1, max = 200)
		public final int minColonyDistance = 8;
		@ConfigEntry.BoundedDiscrete(min = 1, max = 200)
		public final int initialColonySize = 4;
		public final boolean restrictColonyPlacement = false;
		@ConfigEntry.BoundedDiscrete(min = 1000, max = 100000)
		public final int maxDistanceFromWorldSpawn = 8000;
		@ConfigEntry.BoundedDiscrete(min = 1, max = 1000)
		public final int minDistanceFromWorldSpawn = 512;
		public final boolean officersReceiveAdvancements = true;
	}

	public static class Combat {
		public final boolean doBarbariansSpawn;
		public final int barbarianHordeDifficulty;
		public final int spawnBarbarianSize;
		public final int maxBarbarianSize;
		public final boolean doBarbariansBreakThroughWalls;
		public final int averageNumberOfNightsBetweenRaids;
		public final int minimumNumberOfNightsBetweenRaids;
		public final boolean shouldRaidersBreakDoors;
		public final boolean mobAttackCitizens;
		public final boolean citizenCallForHelp;
		public final boolean rangerEnchants;
		public final double rangerDamageMult;
		public final double knightDamageMult;
		public final double guardHealthMult;
		public final boolean pvp_mode;
		public final int daysUntilPirateshipsDespawn;
		public final int maxYForBarbarians;
	}

	public static class Permissions {
		public final boolean enableColonyProtection;
		public final boolean turnOffExplosionsInColonies;
		public final List<? extends String> specialPermGroup;
		public final List<? extends String> freeToInteractBlocks;
		public final int secondsBetweenPermissionMessages;
	}

	public static class Compatibility {
		public final List<? extends String> enabledModTags = Arrays.asList(
				"minecraft:wool",
				"minecraft:planks",
				"minecraft:stone_bricks",
				"minecraft:wooden_buttons",
				"minecraft:buttons",
				"minecraft:carpets",
				"minecraft:wooden_doors",
				"minecraft:wooden_stairs",
				"minecraft:wooden_slabs",
				"minecraft:wooden_fences",
				"minecraft:wooden_pressure_plates",
				"minecraft:wooden_trapdoors",
				"minecraft:saplings",
				"minecraft:logs",
				"minecraft:dark_oak_logs",
				"minecraft:oak_logs",
				"minecraft:birch_logs",
				"minecraft:acacia_logs",
				"minecraft:jungle_logs",
				"minecraft:spruce_logs",
				"minecraft:banners",
				"minecraft:sand",
				"minecraft:walls",
				"minecraft:anvil",
				"minecraft:leaves",
				"minecraft:small_flowers",
				"minecraft:beds",
				"minecraft:fishes",
				"minecraft:signs",
				"minecraft:music_discs",
				"minecraft:arrows");
		public final List<? extends String> guardResourceLocations = Collections.singletonList("minecraft:slime");
		public final List<? extends String> configListStudyItems = Arrays.asList("minecraft:paper;400;100", "minecraft:book;600;10");
		public final List<? extends String> configListRecruitmentItems = Arrays.asList(
				"minecraft:hay_block;2",
				"minecraft:book;2",
				"minecraft:enchanted_book;9",
				"minecraft:diamond;9",
				"minecraft:emerald;8",
				"minecraft:baked_potato;1",
				"minecraft:gold_ingot;2",
				"minecraft:redstone;2",
				"minecraft:lapis_lazuli;2",
				"minecraft:cake;7",
				"minecraft:sunflower;5",
				"minecraft:honeycomb;6",
				"minecraft:quartz;3");
		public final List<? extends String> luckyOres = Arrays.asList(
				"minecraft:coal_ore!64",
				"minecraft:iron_ore!32",
				"minecraft:gold_ore!16",
				"minecraft:redstone_ore!8",
				"minecraft:lapis_ore!4",
				"minecraft:diamond_ore!2",
				"minecraft:emerald_ore!1");
		public final List<? extends String> crusherProduction = Arrays.asList(
				"minecraft:cobblestone!minecraft:gravel",
				"minecraft:gravel!minecraft:sand",
				"minecraft:sand!minecraft:clay");
		public final List<? extends String> sifterMeshes = Arrays.asList(
				"minecraft:string,0",
				"minecraft:flint,0.1",
				"minecraft:iron_ingot,0.1",
				"minecraft:diamond,0.1");
		public final List<? extends String> listOfPlantables = Arrays.asList(
				"minecraft:sunflower",
				"minecraft:lilac",
				"minecraft:rose_bush",
				"minecraft:peony",
				"minecraft:tall_grass",
				"minecraft:large_fern",
				"minecraft:fern",
				"minecraft:small_flowers");
		public final List<? extends String> enchantments = Arrays.asList(
				"1,minecraft:aqua_affinity,1,50",
				"1,minecraft:bane_of_arthropods,1,50",
				"1,minecraft:blast_protection,1,50",
				"1,minecraft:depth_strider,1,50",
				"1,minecraft:feather_falling,1,50",
				"1,minecraft:fire_aspect,1,50",
				"1,minecraft:fire_protection,1,50",
				"1,minecraft:flame,1,50",
				"1,minecraft:frost_walker,1,50",
				"1,minecraft:knockback,1,50",
				"1,minecraft:looting,1,50",
				"1,minecraft:power,1,50",
				"1,minecraft:projectile_protection,1,50",
				"1,minecraft:protection,1,50",
				"1,minecraft:punch,1,50",
				"1,minecraft:respiration,1,50",
				"1,minecraft:sharpness,1,50",
				"1,minecraft:smite,1,50",
				"1,minecraft:sweeping,1,50",
				"1,minecraft:unbreaking,1,50",
				"3,minecolonies:raider_damage_enchant,1,15",

				"2,minecraft:aqua_affinity,2,25",
				"2,minecraft:bane_of_arthropods,2,25",
				"2,minecraft:blast_protection,2,25",
				"2,minecraft:depth_strider,2,25",
				"2,minecraft:feather_falling,2,25",
				"2,minecraft:fire_aspect,2,25",
				"2,minecraft:fire_protection,2,25",
				"2,minecraft:flame,2,25",
				"2,minecraft:frost_walker,2,25",
				"2,minecraft:knockback,2,25",
				"2,minecraft:looting,2,25",
				"2,minecraft:power,2,25",
				"2,minecraft:projectile_protection,2,25",
				"2,minecraft:protection,2,25",
				"2,minecraft:punch,2,25",
				"2,minecraft:respiration,2,25",
				"2,minecraft:sharpness,2,25",
				"2,minecraft:smite,2,25",
				"2,minecraft:sweeping,2,25",
				"2,minecraft:unbreaking,2,25",

				"3,minecraft:aqua_affinity,3,15",
				"3,minecraft:bane_of_arthropods,3,15",
				"3,minecraft:blast_protection,3,15",
				"3,minecraft:depth_strider,3,15",
				"3,minecraft:feather_falling,3,15",
				"3,minecraft:fire_aspect,3,15",
				"3,minecraft:fire_protection,3,15",
				"3,minecraft:flame,3,15",
				"3,minecraft:frost_walker,3,15",
				"3,minecraft:knockback,3,15",
				"3,minecraft:looting,3,15",
				"3,minecraft:power,3,15",
				"3,minecraft:projectile_protection,3,15",
				"3,minecraft:protection,3,15",
				"3,minecraft:punch,3,15",
				"3,minecraft:respiration,3,15",
				"3,minecraft:sharpness,3,15",
				"3,minecraft:smite,3,15",
				"3,minecraft:sweeping,3,15",
				"3,minecraft:unbreaking,3,15",

				"4,minecraft:aqua_affinity,4,5",
				"4,minecraft:bane_of_arthropods,4,5",
				"4,minecraft:blast_protection,4,5",
				"4,minecraft:depth_strider,4,5",
				"4,minecraft:feather_falling,4,5",
				"4,minecraft:fire_aspect,4,5",
				"4,minecraft:fire_protection,4,5",
				"4,minecraft:flame,4,5",
				"4,minecraft:frost_walker,4,5",
				"4,minecraft:infinity,1,5",
				"4,minecraft:knockback,4,5",
				"4,minecraft:looting,4,5",
				"4,minecraft:power,4,5",
				"4,minecraft:projectile_protection,4,5",
				"4,minecraft:protection,4,5",
				"4,minecraft:punch,4,5",
				"4,minecraft:respiration,4,5",
				"4,minecraft:sharpness,4,5",
				"4,minecraft:smite,4,5",
				"4,minecraft:sweeping,4,5",
				"4,minecraft:unbreaking,4,5",

				"5,minecraft:aqua_affinity,5,1",
				"5,minecraft:bane_of_arthropods,5,1",
				"5,minecraft:blast_protection,5,1",
				"5,minecraft:depth_strider,5,1",
				"5,minecraft:feather_falling,5,1",
				"5,minecraft:fire_aspect,5,1",
				"5,minecraft:fire_protection,5,1",
				"5,minecraft:flame,5,1",
				"5,minecraft:frost_walker,5,1",
				"5,minecraft:infinity,1,1",
				"5,minecraft:knockback,5,1",
				"5,minecraft:looting,5,1",
				"5,minecraft:mending,1,1",
				"5,minecraft:power,5,1",
				"5,minecraft:projectile_protection,5,1",
				"5,minecraft:protection,5,1",
				"5,minecraft:punch,5,1",
				"5,minecraft:respiration,5,1",
				"5,minecraft:sharpness,5,1",
				"5,minecraft:smite,5,1",
				"5,minecraft:sweeping,5,1",
				"5,minecolonies:raider_damage_enchant,2,3",
				"5,minecraft:unbreaking,5,1");
		public final double enchanterExperienceMultiplier;
		public final int dynamicTreeHarvestSize;
		public final int fishingRodDurabilityAdjustT1;
		public final int fishingRodDurabilityAdjustT2;
		public final List<? extends String> diseases;
		public final boolean debugInventories;
	}

	public static class Pathfinding {
		public final int pathfindingDebugVerbosity;
		public final int pathfindingMaxThreadCount;
		public final int pathfindingMaxNodes;
		public final int minimumRailsToPath;
	}

	public static class RequestSystem {
		public final boolean enableDebugLogging;
		public final int maximalRetries;
		public final int delayBetweenRetries;
		public final boolean creativeResolve;
		public final boolean canPlayerUseResetCommand;
	}

	public static class Research {
		public final List<? extends String> tactictraining;

		public final List<? extends String> improvedswords;
		public final List<? extends String> squiretraining;
		public final List<? extends String> knighttraining;
		public final List<? extends String> captaintraining;
		public final List<? extends String> captainoftheguard;

		public final List<? extends String> improvedbows;
		public final List<? extends String> tickshot;
		public final List<? extends String> multishot;
		public final List<? extends String> rapidshot;
		public final List<? extends String> masterbowman;

		public final List<? extends String> avoidance;

		public final List<? extends String> parry;
		public final List<? extends String> repost;
		public final List<? extends String> duelist;
		public final List<? extends String> provost;
		public final List<? extends String> masterswordsman;

		public final List<? extends String> dodge;
		public final List<? extends String> taunt;
		public final List<? extends String> improveddodge;
		public final List<? extends String> evasion;
		public final List<? extends String> improvedevasion;
		public final List<? extends String> agilearcher;

		public final List<? extends String> improvedleather;
		public final List<? extends String> boiledleather;
		public final List<? extends String> ironskin;
		public final List<? extends String> ironarmour;
		public final List<? extends String> steelarmour;
		public final List<? extends String> diamondskin;

		public final List<? extends String> regeneration;
		public final List<? extends String> avoid;
		public final List<? extends String> evade;
		public final List<? extends String> flee;
		public final List<? extends String> hotfoot;

		public final List<? extends String> feint;
		public final List<? extends String> fear;
		public final List<? extends String> retreat;
		public final List<? extends String> fullretreat;

		public final List<? extends String> accuracy;
		public final List<? extends String> quickdraw;
		public final List<? extends String> powerattack;
		public final List<? extends String> cleave;
		public final List<? extends String> mightycleave;
		public final List<? extends String> whirlwind;

		public final List<? extends String> preciseshot;
		public final List<? extends String> penetratingshot;
		public final List<? extends String> piercingshot;
		public final List<? extends String> woundingshot;
		public final List<? extends String> deadlyaim;

		public final List<? extends String> higherlearning;

		public final List<? extends String> morebooks;
		public final List<? extends String> bookworm;
		public final List<? extends String> bachelor;
		public final List<? extends String> master;
		public final List<? extends String> phd;

		public final List<? extends String> nurture;
		public final List<? extends String> hormones;
		public final List<? extends String> puberty;
		public final List<? extends String> growth;
		public final List<? extends String> beanstalk;

		public final List<? extends String> keen;
		public final List<? extends String> outpost;
		public final List<? extends String> hamlet;
		public final List<? extends String> village;
		public final List<? extends String> city;

		public final List<? extends String> diligent;
		public final List<? extends String> studious;
		public final List<? extends String> scholarly;
		public final List<? extends String> reflective;
		public final List<? extends String> academic;

		public final List<? extends String> rails;
		public final List<? extends String> nimble;
		public final List<? extends String> agile;
		public final List<? extends String> swift;
		public final List<? extends String> athlete;

		public final List<? extends String> stamina;

		public final List<? extends String> resistance;
		public final List<? extends String> resilience;
		public final List<? extends String> vitality;
		public final List<? extends String> fortitude;
		public final List<? extends String> indefatigability;

		public final List<? extends String> bandaid;
		public final List<? extends String> healingcream;
		public final List<? extends String> bandages;
		public final List<? extends String> compress;
		public final List<? extends String> cast;

		public final List<? extends String> gourmand;
		public final List<? extends String> gorger;
		public final List<? extends String> stuffer;
		public final List<? extends String> epicure;
		public final List<? extends String> glutton;

		public final List<? extends String> circus;
		public final List<? extends String> festival;
		public final List<? extends String> nightowl;
		public final List<? extends String> spectacle;
		public final List<? extends String> nightowl2;
		public final List<? extends String> opera;
		public final List<? extends String> theater;

		public final List<? extends String> firstaid;
		public final List<? extends String> firstaid2;
		public final List<? extends String> livesaver;
		public final List<? extends String> livesaver2;
		public final List<? extends String> guardianangel;
		public final List<? extends String> guardianangel2;

		public final List<? extends String> whatyaneed;
		public final List<? extends String> enhanced_gates1;
		public final List<? extends String> enhanced_gates2;
		public final List<? extends String> stringwork;
		public final List<? extends String> thoselungs;
		public final List<? extends String> rainbowheaven;

		public final List<? extends String> veinminer;
		public final List<? extends String> goodveins;
		public final List<? extends String> richveins;
		public final List<? extends String> amazingveins;
		public final List<? extends String> motherlode;

		public final List<? extends String> ability;
		public final List<? extends String> skills;
		public final List<? extends String> tools;
		public final List<? extends String> seemsautomatic;
		public final List<? extends String> madness;

		public final List<? extends String> hittingiron;
		public final List<? extends String> stonecake;
		public final List<? extends String> strong;
		public final List<? extends String> hardened;
		public final List<? extends String> reinforced;
		public final List<? extends String> steelbracing;
		public final List<? extends String> diamondcoated;

		public final List<? extends String> memoryaid;
		public final List<? extends String> cheatsheet;
		public final List<? extends String> recipebook;
		public final List<? extends String> rtm;
		public final List<? extends String> rainman;

		public final List<? extends String> woodwork;
		public final List<? extends String> sieving;
		public final List<? extends String> space;
		public final List<? extends String> capacity;
		public final List<? extends String> fullstock;

		public final List<? extends String> theflintstones;
		public final List<? extends String> rockingroll;

		public final List<? extends String> hot;
		public final List<? extends String> isthisredstone;
		public final List<? extends String> redstonepowered;
		public final List<? extends String> heavymachinery;
		public final List<? extends String> whatisthisspeed;

		public final List<? extends String> biodegradable;
		public final List<? extends String> flowerpower;

		public final List<? extends String> letitgrow;

		public final List<? extends String> bonemeal;
		public final List<? extends String> dung;
		public final List<? extends String> compost;
		public final List<? extends String> fertilizer;
		public final List<? extends String> magiccompost;
		public final List<? extends String> lightning;

		public final List<? extends String> deeppockets;
		public final List<? extends String> loaded;
		public final List<? extends String> heavilyloaded;

		public final List<? extends String> gildedhammer;
		public final List<? extends String> doubletrouble;
		public final List<? extends String> hotboots;
		public final List<? extends String> pavetheroad;
		public final List<? extends String> arrowuse;
		public final List<? extends String> arrowpierce;
		public final List<? extends String> knockbackaoe;

		public final List<? extends String> knowtheend;
		public final List<? extends String> morescrolls;
	}

	/**
	 * Builds server configuration.
	 *
	 * @param builder config builder
	 */
	protected ServerConfiguration(final ForgeConfigSpec.Builder builder) {
		allowGlobalNameChanges = defineInteger(builder, "allowglobalnamechanges", 1, -1, 1);
		holidayFeatures = defineBoolean(builder, "holidayfeatures", true);
		updateRate = defineInteger(builder, "updaterate", 1, 1, 100);
		dirtFromCompost = defineInteger(builder, "dirtfromcompost", 1, 0, 100);
		luckyBlockChance = defineInteger(builder, "luckyblockchance", 1, 0, 100);
		fixOrphanedChunks = defineBoolean(builder, "fixorphanedchunks", false);
		restrictBuilderUnderground = defineBoolean(builder, "restrictbuilderunderground", true);
		fisherSpongeChance = defineDouble(builder, "fisherspongechance", 0.1, 0, 100);
		fisherPrismarineChance = defineDouble(builder, "fisherprismarinechance", 2.5, 0, 100);
		minThLevelToTeleport = defineInteger(builder, "minthleveltoteleport", 3, 0, 5);
		suggestBuildToolPlacement = defineBoolean(builder, "suggestbuildtoolplacement", true);
		foodModifier = defineDouble(builder, "foodmodifier", 1.0, 0.1, 100);
		diseaseModifier = defineInteger(builder, "diseasemodifier", 5, 1, 100);
		forceLoadColony = defineBoolean(builder, "forceloadcolony", false);
		badVisitorsChance = defineInteger(builder, "badvisitorchance", 2, 1, 100);

		swapToCategory(builder, "commands");

		canPlayerUseRTPCommand = defineBoolean(builder, "canplayerusertpcommand", true);
		canPlayerUseColonyTPCommand = defineBoolean(builder, "canplayerusecolonytpcommand", false);
		canPlayerUseAllyTHTeleport = defineBoolean(builder, "canplayeruseallytownhallteleport", true);
		canPlayerUseHomeTPCommand = defineBoolean(builder, "canplayerusehometpcommand", true);
		canPlayerUseShowColonyInfoCommand = defineBoolean(builder, "canplayeruseshowcolonyinfocommand", true);
		canPlayerUseKillCitizensCommand = defineBoolean(builder, "canplayerusekillcitizenscommand", true);
		canPlayerUseAddOfficerCommand = defineBoolean(builder, "canplayeruseaddofficercommand", true);
		canPlayerUseDeleteColonyCommand = defineBoolean(builder, "canplayerusedeletecolonycommand", true);
		numberOfAttemptsForSafeTP = defineInteger(builder, "numberofattemptsforsafetp", 4, 1, 10);


		swapToCategory(builder, "claims");

		maxColonySize = defineInteger(builder, "maxColonySize", 20, 1, 50);
		minColonyDistance = defineInteger(builder, "minColonyDistance", 8, 1, 200);
		initialColonySize = defineInteger(builder, "initialColonySize", 4, 1, 200);
		restrictColonyPlacement = defineBoolean(builder, "restrictcolonyplacement", false);
		maxDistanceFromWorldSpawn = defineInteger(builder, "maxdistancefromworldspawn", 8000, 1000, 100000);
		minDistanceFromWorldSpawn = defineInteger(builder, "mindistancefromworldspawn", 512, 1, 1000);
		officersReceiveAdvancements = defineBoolean(builder, "officersreceiveadvancements", true);

		swapToCategory(builder, "combat");

		doBarbariansSpawn = defineBoolean(builder, "dobarbariansspawn", true);
		barbarianHordeDifficulty = defineInteger(builder, "barbarianhordedifficulty", DEFAULT_BARBARIAN_DIFFICULTY, MIN_BARBARIAN_DIFFICULTY, MAX_BARBARIAN_DIFFICULTY);
		spawnBarbarianSize = defineInteger(builder, "spawnbarbariansize", 5, MIN_SPAWN_BARBARIAN_HORDE_SIZE, MAX_SPAWN_BARBARIAN_HORDE_SIZE);
		maxBarbarianSize = defineInteger(builder, "maxBarbarianSize", 80, MIN_BARBARIAN_HORDE_SIZE, MAX_BARBARIAN_HORDE_SIZE);
		doBarbariansBreakThroughWalls = defineBoolean(builder, "dobarbariansbreakthroughwalls", true);
		averageNumberOfNightsBetweenRaids = defineInteger(builder, "averagenumberofnightsbetweenraids", 12, 1, 50);
		minimumNumberOfNightsBetweenRaids = defineInteger(builder, "minimumnumberofnightsbetweenraids", 8, 1, 30);
		mobAttackCitizens = defineBoolean(builder, "mobattackcitizens", true);
		shouldRaidersBreakDoors = defineBoolean(builder, "shouldraiderbreakdoors", true);
		citizenCallForHelp = defineBoolean(builder, "citizencallforhelp", true);
		rangerEnchants = defineBoolean(builder, "rangerenchants", true);
		rangerDamageMult = defineDouble(builder, "rangerdamagemult", 1.0, 0.1, 5.0);
		knightDamageMult = defineDouble(builder, "knightdamagemult", 1.0, 0.1, 5.0);
		guardHealthMult = defineDouble(builder, "guardhealthmult", 1.0, 0.1, 5.0);
		pvp_mode = defineBoolean(builder, "pvp_mode", false);
		daysUntilPirateshipsDespawn = defineInteger(builder, "daysuntilpirateshipsdespawn", 3, 1, 10);
		maxYForBarbarians = defineInteger(builder, "maxyforbarbarians", 200, 1, 500);

		swapToCategory(builder, "permissions");

		enableColonyProtection = defineBoolean(builder, "enablecolonyprotection", true);
		turnOffExplosionsInColonies = defineBoolean(builder, "turnoffexplosionsincolonies", true);
		specialPermGroup = defineList(builder, "specialpermgroup",
				Arrays.asList
						("_Raycoms_"),
				s -> s instanceof String);
		freeToInteractBlocks = defineList(builder, "freetointeractblocks",
				Arrays.asList
						("dirt",
								"0 0 0"),
				s -> s instanceof String);
		secondsBetweenPermissionMessages = defineInteger(builder, "secondsBetweenPermissionMessages", 30, 1, 1000);


		enchanterExperienceMultiplier = defineDouble(builder, "enchanterexperiencemultiplier", 2, 1, 10);

		dynamicTreeHarvestSize = defineInteger(builder, "dynamictreeharvestsize", 5, 1, 5);

		fishingRodDurabilityAdjustT2 = defineInteger(builder, "fishingroddurabilityadjustt2", 6, -249, 250000);
		fishingRodDurabilityAdjustT1 = defineInteger(builder, "fishingroddurabilityadjustt1", 22, -58, 250000);

		diseases = defineList(builder, "diseases",
				Arrays.asList("Influenza,100,minecraft:carrot,minecraft:potato",
						"Measles,10,minecraft:dandelion,minecraft:kelp,minecraft:poppy",
						"Smallpox,1,minecraft:honey_bottle,minecraft:golden_apple"),
				s -> s instanceof String);

		debugInventories = defineBoolean(builder, "debuginventories", false);

		swapToCategory(builder, "pathfinding");

		pathfindingDebugVerbosity = defineInteger(builder, "pathfindingdebugverbosity", 0, 0, 10);
		minimumRailsToPath = defineInteger(builder, "minimumrailstopath", 5, 5, 100);
		pathfindingMaxThreadCount = defineInteger(builder, "pathfindingmaxthreadcount", 2, 1, 10);
		pathfindingMaxNodes = defineInteger(builder, "pathfindingmaxnodes", 5000, 1, 10000);

		swapToCategory(builder, "requestSystem");

		enableDebugLogging = defineBoolean(builder, "enabledebuglogging", false);
		maximalRetries = defineInteger(builder, "maximalretries", 3, 1, 10);
		delayBetweenRetries = defineInteger(builder, "delaybetweenretries", 1200, 30, 10000);
		creativeResolve = defineBoolean(builder, "creativeresolve", false);
		canPlayerUseResetCommand = defineBoolean(builder, "canplayeruseresetcommand", false);

		swapToCategory(builder, "research");

		tactictraining = defineList(builder, "tactictraining",
				Collections.singletonList("minecraft:iron_block*3"),
				s -> s instanceof String);

		improvedswords = defineList(builder, "improvedswords",
				Collections.singletonList("minecraft:iron_block*6"),
				s -> s instanceof String);
		squiretraining = defineList(builder, "squiretraining",
				Collections.singletonList("minecraft:shield*4"),
				s -> s instanceof String);
		knighttraining = defineList(builder, "knighttraining",
				Collections.singletonList("minecraft:shield*8"),
				s -> s instanceof String);
		captaintraining = defineList(builder, "captaintraining",
				Collections.singletonList("minecraft:shield*16"),
				s -> s instanceof String);
		captainoftheguard = defineList(builder, "captainoftheguard",
				Collections.singletonList("minecraft:shield*27"),
				s -> s instanceof String);

		improvedbows = defineList(builder, "improvedbows",
				Collections.singletonList("minecraft:iron_block*6"),
				s -> s instanceof String);
		tickshot = defineList(builder, "tickshot",
				Collections.singletonList("minecraft:bow*5"),
				s -> s instanceof String);
		multishot = defineList(builder, "multishot",
				Collections.singletonList("minecraft:bow*9"),
				s -> s instanceof String);
		rapidshot = defineList(builder, "rapidshot",
				Collections.singletonList("minecraft:bow*18"),
				s -> s instanceof String);
		masterbowman = defineList(builder, "masterbowman",
				Collections.singletonList("minecraft:bow*27"),
				s -> s instanceof String);

		avoidance = defineList(builder, "avoidance",
				Collections.singletonList("minecraft:iron_block*3"),
				s -> s instanceof String);

		parry = defineList(builder, "parry",
				Collections.singletonList("minecraft:iron_ingot*16"),
				s -> s instanceof String);
		repost = defineList(builder, "repost",
				Collections.singletonList("minecraft:iron_ingot*32"),
				s -> s instanceof String);
		duelist = defineList(builder, "duelist",
				Collections.singletonList("minecraft:iron_ingot*64"),
				s -> s instanceof String);
		provost = defineList(builder, "provost",
				Collections.singletonList("minecraft:diamond*16"),
				s -> s instanceof String);
		masterswordsman = defineList(builder, "masterswordsman",
				Collections.singletonList("minecraft:diamond*64"),
				s -> s instanceof String);

		dodge = defineList(builder, "dodge",
				Collections.singletonList("minecraft:leather*16"),
				s -> s instanceof String);
		improveddodge = defineList(builder, "improveddodge",
				Collections.singletonList("minecraft:leather*32"),
				s -> s instanceof String);
		evasion = defineList(builder, "evasion",
				Collections.singletonList("minecraft:leather*64"),
				s -> s instanceof String);
		improvedevasion = defineList(builder, "improvedevasion",
				Collections.singletonList("minecraft:diamond*16"),
				s -> s instanceof String);
		agilearcher = defineList(builder, "agilearcher",
				Collections.singletonList("minecraft:diamond*64"),
				s -> s instanceof String);

		this.improvedleather = defineList(builder, "improvedleather",
				Collections.singletonList("minecraft:leather*32"),
				s -> s instanceof String);
		this.boiledleather = defineList(builder, "boiledleather",
				Collections.singletonList("minecraft:leather*64"),
				s -> s instanceof String);
		this.ironskin = defineList(builder, "ironskin",
				Collections.singletonList("minecraft:iron_ingot*16"),
				s -> s instanceof String);
		this.ironarmour = defineList(builder, "ironarmour",
				Collections.singletonList("minecraft:iron_ingot*32"),
				s -> s instanceof String);
		this.steelarmour = defineList(builder, "steelarmour",
				Collections.singletonList("minecraft:iron_ingot*64"),
				s -> s instanceof String);
		this.diamondskin = defineList(builder, "diamondskin",
				Collections.singletonList("minecraft:diamond*64"),
				s -> s instanceof String);

		this.regeneration = defineList(builder, "regeneration",
				Collections.singletonList("minecraft:emerald*1"),
				s -> s instanceof String);

		this.feint = defineList(builder, "feint",
				Collections.singletonList("minecraft:emerald*8"),
				s -> s instanceof String);
		this.fear = defineList(builder, "fear",
				Collections.singletonList("minecraft:emerald*16"),
				s -> s instanceof String);
		this.retreat = defineList(builder, "retreat",
				Collections.singletonList("minecraft:emerald*32"),
				s -> s instanceof String);
		this.fullretreat = defineList(builder, "fullretreat",
				Collections.singletonList("minecraft:emerald*64"),
				s -> s instanceof String);

		this.avoid = defineList(builder, "avoid",
				Collections.singletonList("minecraft:emerald*8"),
				s -> s instanceof String);
		this.evade = defineList(builder, "evade",
				Collections.singletonList("minecraft:emerald*16"),
				s -> s instanceof String);
		this.flee = defineList(builder, "flee",
				Collections.singletonList("minecraft:emerald*32"),
				s -> s instanceof String);
		this.hotfoot = defineList(builder, "hotfoot",
				Collections.singletonList("minecraft:emerald*64"),
				s -> s instanceof String);

		this.accuracy = defineList(builder, "accuracy",
				Collections.singletonList("minecraft:iron_ingot*16"),
				s -> s instanceof String);

		this.quickdraw = defineList(builder, "quickdraw",
				Collections.singletonList("minecraft:iron_block*2"),
				s -> s instanceof String);
		this.powerattack = defineList(builder, "powerattack",
				Collections.singletonList("minecraft:iron_block*4"),
				s -> s instanceof String);
		this.cleave = defineList(builder, "cleave",
				Collections.singletonList("minecraft:iron_block*8"),
				s -> s instanceof String);
		this.mightycleave = defineList(builder, "mightycleave",
				Collections.singletonList("minecraft:iron_block*16"),
				s -> s instanceof String);
		this.whirlwind = defineList(builder, "whirlwind",
				Collections.singletonList("minecraft:iron_block*32"),
				s -> s instanceof String);

		this.preciseshot = defineList(builder, "preciseshot",
				Collections.singletonList("minecraft:flint*16"),
				s -> s instanceof String);
		this.penetratingshot = defineList(builder, "penetratingshot",
				Collections.singletonList("minecraft:flint*32"),
				s -> s instanceof String);
		this.piercingshot = defineList(builder, "piercingshot",
				Collections.singletonList("minecraft:flint*64"),
				s -> s instanceof String);
		this.woundingshot = defineList(builder, "woundingshot",
				Collections.singletonList("minecraft:flint*128"),
				s -> s instanceof String);
		this.deadlyaim = defineList(builder, "deadlyaim",
				Collections.singletonList("minecraft:flint*256"),
				s -> s instanceof String);

		this.higherlearning = defineList(builder, "higherlearning",
				Collections.singletonList("minecraft:book*3"),
				s -> s instanceof String);

		this.morebooks = defineList(builder, "morebooks",
				Collections.singletonList("minecraft:book*6"),
				s -> s instanceof String);
		this.bookworm = defineList(builder, "bookworm",
				Collections.singletonList("minecraft:bookshelf*6"),
				s -> s instanceof String);
		this.bachelor = defineList(builder, "bachelor",
				Collections.singletonList("minecraft:bookshelf*12"),
				s -> s instanceof String);
		this.master = defineList(builder, "master",
				Collections.singletonList("minecraft:bookshelf*32"),
				s -> s instanceof String);
		this.phd = defineList(builder, "phd",
				Collections.singletonList("minecraft:bookshelf*64"),
				s -> s instanceof String);

		this.nurture = defineList(builder, "nurture",
				Collections.singletonList("minecraft:cooked_chicken*32"),
				s -> s instanceof String);
		this.hormones = defineList(builder, "hormones",
				Collections.singletonList("minecraft:cooked_chicken*64"),
				s -> s instanceof String);
		this.puberty = defineList(builder, "puberty",
				Collections.singletonList("minecraft:cooked_chicken*128"),
				s -> s instanceof String);
		this.growth = defineList(builder, "growth",
				Collections.singletonList("minecraft:cooked_chicken*256"),
				s -> s instanceof String);
		this.beanstalk = defineList(builder, "beanstalk",
				Collections.singletonList("minecraft:cooked_chicken*512"),
				s -> s instanceof String);

		this.keen = defineList(builder, "keen",
				Collections.singletonList("minecraft:book*3"),
				s -> s instanceof String);
		this.outpost = defineList(builder, "outpost",
				Collections.singletonList("minecraft:cooked_beef*64"),
				s -> s instanceof String);
		this.hamlet = defineList(builder, "hamlet",
				Collections.singletonList("minecraft:cooked_beef*128"),
				s -> s instanceof String);
		this.village = defineList(builder, "village",
				Collections.singletonList("minecraft:cooked_beef*256"),
				s -> s instanceof String);
		this.city = defineList(builder, "city",
				Collections.singletonList("minecraft:cooked_beef*512"),
				s -> s instanceof String);

		this.diligent = defineList(builder, "diligent",
				Collections.singletonList("minecraft:book*6"),
				s -> s instanceof String);
		this.studious = defineList(builder, "studious",
				Collections.singletonList("minecraft:book*12"),
				s -> s instanceof String);
		this.scholarly = defineList(builder, "scholarly",
				Collections.singletonList("minecraft:book*24"),
				s -> s instanceof String);
		this.reflective = defineList(builder, "reflective",
				Collections.singletonList("minecraft:book*48"),
				s -> s instanceof String);
		this.academic = defineList(builder, "academic",
				Collections.singletonList("minecraft:book*128"),
				s -> s instanceof String);

		this.rails = defineList(builder, "rails",
				Collections.singletonList("minecraft:rail*64"),
				s -> s instanceof String);
		this.nimble = defineList(builder, "nimble",
				Collections.singletonList("minecraft:rabbit_foot*1"),
				s -> s instanceof String);
		this.agile = defineList(builder, "agile",
				Collections.singletonList("minecraft:rabbit_foot*10"),
				s -> s instanceof String);
		this.swift = defineList(builder, "swift",
				Collections.singletonList("minecraft:rabbit_foot*32"),
				s -> s instanceof String);
		this.athlete = defineList(builder, "athlete",
				Collections.singletonList("minecraft:rabbit_foot*64"),
				s -> s instanceof String);

		this.stamina = defineList(builder, "stamina",
				Collections.singletonList("minecraft:carrot*1"),
				s -> s instanceof String);

		this.resistance = defineList(builder, "resistance",
				Collections.singletonList("minecraft:golden_apple*1"),
				s -> s instanceof String);
		this.resilience = defineList(builder, "resilience",
				Collections.singletonList("minecraft:golden_apple*8"),
				s -> s instanceof String);
		this.vitality = defineList(builder, "vitality",
				Collections.singletonList("minecraft:golden_apple*16"),
				s -> s instanceof String);
		this.fortitude = defineList(builder, "fortitude",
				Collections.singletonList("minecraft:golden_apple*32"),
				s -> s instanceof String);
		this.indefatigability = defineList(builder, "indefatigability",
				Collections.singletonList("minecraft:golden_apple*64"),
				s -> s instanceof String);

		this.bandaid = defineList(builder, "bandaid",
				Collections.singletonList("minecraft:golden_carrot*1"),
				s -> s instanceof String);
		this.healingcream = defineList(builder, "healingcream",
				Collections.singletonList("minecraft:golden_carrot*8"),
				s -> s instanceof String);
		this.bandages = defineList(builder, "bandages",
				Collections.singletonList("minecraft:golden_carrot*16"),
				s -> s instanceof String);
		this.compress = defineList(builder, "compress",
				Collections.singletonList("minecraft:golden_carrot*32"),
				s -> s instanceof String);
		this.cast = defineList(builder, "cast",
				Collections.singletonList("minecraft:golden_carrot*64"),
				s -> s instanceof String);

		this.gourmand = defineList(builder, "gourmand",
				Collections.singletonList("minecraft:cookie*32"),
				s -> s instanceof String);
		this.gorger = defineList(builder, "gorger",
				Collections.singletonList("minecraft:cookie*64"),
				s -> s instanceof String);
		this.stuffer = defineList(builder, "stuffer",
				Collections.singletonList("minecraft:cookie*128"),
				s -> s instanceof String);
		this.epicure = defineList(builder, "epicure",
				Collections.singletonList("minecraft:cookie*256"),
				s -> s instanceof String);
		this.glutton = defineList(builder, "glutton",
				Collections.singletonList("minecraft:cookie*512"),
				s -> s instanceof String);

		this.circus = defineList(builder, "circus",
				Collections.singletonList("minecraft:cake*1"),
				s -> s instanceof String);
		this.festival = defineList(builder, "festival",
				Collections.singletonList("minecraft:cake*9"),
				s -> s instanceof String);
		this.nightowl = defineList(builder, "nightowl",
				Collections.singletonList("minecraft:golden_carrot*25"),
				s -> s instanceof String);
		this.spectacle = defineList(builder, "spectacle",
				Collections.singletonList("minecraft:cake*18"),
				s -> s instanceof String);
		this.nightowl2 = defineList(builder, "nightowl2",
				Collections.singletonList("minecraft:golden_carrot*75"),
				s -> s instanceof String);
		this.opera = defineList(builder, "opera",
				Collections.singletonList("minecraft:cake*27"),
				s -> s instanceof String);
		this.theater = defineList(builder, "theater",
				Collections.singletonList("minecraft:enchanted_golden_apple*16"),
				s -> s instanceof String);

		this.firstaid = defineList(builder, "firstaid",
				Collections.singletonList("minecraft:hay_block*8"),
				s -> s instanceof String);
		this.firstaid2 = defineList(builder, "firstaid2",
				Collections.singletonList("minecraft:hay_block*16"),
				s -> s instanceof String);
		this.livesaver = defineList(builder, "livesaver",
				Collections.singletonList("minecraft:hay_block*32"),
				s -> s instanceof String);
		this.livesaver2 = defineList(builder, "livesaver2",
				Collections.singletonList("minecraft:hay_block*64"),
				s -> s instanceof String);
		this.guardianangel = defineList(builder, "guardianangel",
				Collections.singletonList("minecraft:hay_block*128"),
				s -> s instanceof String);
		this.guardianangel2 = defineList(builder, "guardianangel2",
				Collections.singletonList("minecraft:hay_block*256"),
				s -> s instanceof String);

		whatyaneed = defineList(builder, "whatyaneed",
				Collections.singletonList("minecraft:redstone*64"),
				s -> s instanceof String);
		enhanced_gates1 = defineList(builder, "enhanced_gates1",
				Arrays.asList("minecolonies:gate_wood*64", "minecolonies:ancienttome*2", "minecraft:iron_block*5"),
				s -> s instanceof String);
		enhanced_gates2 = defineList(builder, "enhanced_gates2",
				Arrays.asList("minecolonies:gate_iron*64", "minecolonies:ancienttome*2", "minecraft:obsidian*32"), s -> s instanceof String);
		stringwork = defineList(builder, "stringwork",
				Collections.singletonList("minecraft:string*16"),
				s -> s instanceof String);
		thoselungs = defineList(builder, "thoselungs",
				Collections.singletonList("minecraft:glass*64"),
				s -> s instanceof String);
		rainbowheaven = defineList(builder, "rainbowheaven",
				Collections.singletonList("minecraft:poppy*64"),
				s -> s instanceof String);

		this.veinminer = defineList(builder, "veinminer",
				Collections.singletonList("minecraft:iron_ore*32"),
				s -> s instanceof String);
		this.goodveins = defineList(builder, "goodveins",
				Collections.singletonList("minecraft:iron_ore*64"),
				s -> s instanceof String);
		this.richveins = defineList(builder, "richveins",
				Collections.singletonList("minecraft:gold_ore*32"),
				s -> s instanceof String);
		this.amazingveins = defineList(builder, "amazingveins",
				Collections.singletonList("minecraft:gold_ore*64"),
				s -> s instanceof String);
		this.motherlode = defineList(builder, "motherlode",
				Collections.singletonList("minecraft:diamond_ore*64"),
				s -> s instanceof String);

		this.ability = defineList(builder, "ability",
				Collections.singletonList("minecraft:iron_ingot*64"),
				s -> s instanceof String);
		this.skills = defineList(builder, "skills",
				Collections.singletonList("minecraft:iron_ingot*128"),
				s -> s instanceof String);
		this.tools = defineList(builder, "tools",
				Collections.singletonList("minecraft:iron_ingot*256"),
				s -> s instanceof String);
		this.seemsautomatic = defineList(builder, "seemsautomatic",
				Collections.singletonList("minecraft:iron_ingot*512"),
				s -> s instanceof String);
		this.madness = defineList(builder, "madness",
				Collections.singletonList("minecraft:iron_ingot*1024"),
				s -> s instanceof String);

		this.hittingiron = defineList(builder, "hittingiron",
				Collections.singletonList("minecraft:anvil*1"),
				s -> s instanceof String);
		this.stonecake = defineList(builder, "stonecake",
				Collections.singletonList("minecraft:chiseled_stone_bricks*64"),
				s -> s instanceof String);
		this.strong = defineList(builder, "strong",
				Collections.singletonList("minecraft:diamond*8"),
				s -> s instanceof String);
		this.hardened = defineList(builder, "hardened",
				Collections.singletonList("minecraft:diamond*16"),
				s -> s instanceof String);
		this.reinforced = defineList(builder, "reinforced",
				Collections.singletonList("minecraft:diamond*32"),
				s -> s instanceof String);
		this.steelbracing = defineList(builder, "steelbracing",
				Collections.singletonList("minecraft:diamond*64"),
				s -> s instanceof String);
		this.diamondcoated = defineList(builder, "diamondcoated",
				Collections.singletonList("minecraft:diamond*128"),
				s -> s instanceof String);

		this.memoryaid = defineList(builder, "memoryaid",
				Collections.singletonList("minecraft:paper*32"),
				s -> s instanceof String);
		this.cheatsheet = defineList(builder, "cheatsheet",
				Collections.singletonList("minecraft:paper*64"),
				s -> s instanceof String);
		this.recipebook = defineList(builder, "recipebook",
				Collections.singletonList("minecraft:paper*128"),
				s -> s instanceof String);
		this.rtm = defineList(builder, "rtm",
				Collections.singletonList("minecraft:paper*256"),
				s -> s instanceof String);
		this.rainman = defineList(builder, "rainman",
				Collections.singletonList("minecraft:salmon_bucket*27"),
				s -> s instanceof String);

		this.woodwork = defineList(builder, "woodwork",
				Collections.singletonList("minecraft:oak_planks*64"),
				s -> s instanceof String);
		this.sieving = defineList(builder, "sieving",
				Collections.singletonList("minecraft:string*64"),
				s -> s instanceof String);
		this.space = defineList(builder, "space",
				Collections.singletonList("minecolonies:blockminecoloniesrack*16"),
				s -> s instanceof String);
		this.capacity = defineList(builder, "capacity",
				Collections.singletonList("minecolonies:blockminecoloniesrack*32"),
				s -> s instanceof String);
		this.fullstock = defineList(builder, "fullstock",
				Collections.singletonList("minecolonies:blockminecoloniesrack*64"),
				s -> s instanceof String);

		this.theflintstones = defineList(builder, "theflintstones",
				Collections.singletonList("minecraft:stone_bricks*64"),
				s -> s instanceof String);
		this.rockingroll = defineList(builder, "rockingroll",
				Collections.singletonList("minecraft:stone*64"),
				s -> s instanceof String);

		this.hot = defineList(builder, "hot",
				Collections.singletonList("minecraft:lava_bucket*4"),
				s -> s instanceof String);
		this.isthisredstone = defineList(builder, "isthisredstone",
				Collections.singletonList("minecraft:redstone*128"),
				s -> s instanceof String);
		this.redstonepowered = defineList(builder, "redstonepowered",
				Collections.singletonList("minecraft:redstone*256"),
				s -> s instanceof String);
		this.heavymachinery = defineList(builder, "heavymachinery",
				Collections.singletonList("minecraft:redstone*512"),
				s -> s instanceof String);
		this.whatisthisspeed = defineList(builder, "whatisthisspeed",
				Collections.singletonList("minecraft:redstone*1024"),
				s -> s instanceof String);
		this.lightning = defineList(builder, "lightning",
				Collections.singletonList("minecraft:redstone*2048"),
				s -> s instanceof String);

		this.biodegradable = defineList(builder, "biodegradable",
				Collections.singletonList("minecraft:bone_meal*64"),
				s -> s instanceof String);
		this.flowerpower = defineList(builder, "flowerpower",
				Collections.singletonList("minecolonies:compost*64"),
				s -> s instanceof String);

		this.letitgrow = defineList(builder, "letitgrow",
				Collections.singletonList("minecolonies:compost*16"),
				s -> s instanceof String);

		this.bonemeal = defineList(builder, "bonemeal",
				Collections.singletonList("minecraft:wheat_seeds*64"),
				s -> s instanceof String);
		this.dung = defineList(builder, "dung",
				Collections.singletonList("minecraft:wheat_seeds*128"),
				s -> s instanceof String);
		this.compost = defineList(builder, "compost",
				Collections.singletonList("minecraft:wheat_seeds*256"),
				s -> s instanceof String);
		this.fertilizer = defineList(builder, "fertilizer",
				Collections.singletonList("minecraft:wheat_seeds*512"),
				s -> s instanceof String);
		this.magiccompost = defineList(builder, "magiccompost",
				Collections.singletonList("minecraft:wheat_seeds*2048"),
				s -> s instanceof String);

		this.loaded = defineList(builder, "loaded",
				Collections.singletonList("minecraft:emerald*128"),
				s -> s instanceof String);
		this.heavilyloaded = defineList(builder, "heavilyloaded",
				Collections.singletonList("minecraft:emerald*256"),
				s -> s instanceof String);
		this.deeppockets = defineList(builder, "deeppockets",
				Collections.singletonList("minecraft:emerald*64"),
				s -> s instanceof String);

		taunt = defineList(builder, "taunt",
				Arrays.asList("minecraft:rotten_flesh*8", "minecraft:bone*8", "minecraft:spider_eye*8"),
				s -> s instanceof String);
		arrowuse = defineList(builder, "arrowuse",
				Collections.singletonList("minecraft:arrow*64"),
				s -> s instanceof String);
		arrowpierce = defineList(builder, "arrowpierce",
				Arrays.asList("minecraft:arrow*64", "minecraft:redstone*64"),
				s -> s instanceof String);
		knockbackaoe = defineList(builder, "knockbackaoe",
				Arrays.asList("minecraft:redstone*64", "minecraft:gold_ingot*64", "minecraft:lapis_lazuli*128"),
				s -> s instanceof String);

		this.knowtheend = defineList(builder, "knowtheend",
				Arrays.asList("minecraft:chorus_fruit*64"),
				s -> s instanceof String);

		this.morescrolls = defineList(builder, "morescrolls",
				Arrays.asList("minecraft:paper*64", "minecolonies:ancienttome*1", "minecraft:lapis_lazuli*64"),
				s -> s instanceof String);

		this.gildedhammer = defineList(builder, "gildedhammer",
				Arrays.asList("minecraft:gravel*64", "minecraft:sand*64", "minecraft:clay*64"),
				s -> s instanceof String);
		this.doubletrouble = defineList(builder, "doubletrouble",
				Arrays.asList("minecraft:bamboo*64", "minecraft:sugar_cane*64", "minecraft:cactus*64"),
				s -> s instanceof String);
		this.hotboots = defineList(builder, "hotboots",
				Arrays.asList("minecraft:leather*32", "minecraft:iron_ingot*16"),
				s -> s instanceof String);

		this.pavetheroad = defineList(builder, "pavetheroad",
				Collections.singletonList("minecraft:white_concrete*32"),
				s -> s instanceof String);

		finishCategory(builder);
	}
}
