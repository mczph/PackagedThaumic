package thelm.packagedthaumic;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thelm.packagedthaumic.block.BlockInfusionCrafter;
import thelm.packagedthaumic.proxy.CommonProxy;

@Mod(
		modid = PackagedThaumic.MOD_ID,
		name = PackagedThaumic.NAME,
		version = PackagedThaumic.VERSION,
		dependencies = PackagedThaumic.DEPENDENCIES,
		guiFactory = PackagedThaumic.GUI_FACTORY
		)
public class PackagedThaumic {

	public static final String MOD_ID = "packagedthaumic";
	public static final String NAME = "PackagedThaumic";
	public static final String VERSION = "1.12.2-1.0.0.5";
	public static final String DEPENDENCIES = "required-after:packagedauto@[1.12.2-1.0.8,);required-after:thaumcraft;";
	public static final String GUI_FACTORY = "thelm.packagedthaumic.client.gui.GuiPackagedThaumicConfigFactory";
	public static final CreativeTabs CREATIVE_TAB = new CreativeTabs("packagedthaumic") {
		@SideOnly(Side.CLIENT)
		@Override
		public ItemStack createIcon() {
			return new ItemStack(BlockInfusionCrafter.INSTANCE);
		}
	};
	@Instance
	public static PackagedThaumic instance;
	@SidedProxy(clientSide = "thelm.packagedthaumic.proxy.ClientProxy", serverSide = "thelm.packagedthaumic.proxy.CommonProxy", modId = PackagedThaumic.MOD_ID)
	public static CommonProxy proxy;
	public static ModMetadata metadata;

	@EventHandler
	public void firstMovement(FMLPreInitializationEvent event) {
		metadata = event.getModMetadata();
		metadata.autogenerated = false;
		metadata.version = VERSION;
		metadata.authorList.add("TheLMiffy1111");
		metadata.description = "A PackagedAuto addon that adds Thaumcraft autocrafting.";

		MinecraftForge.EVENT_BUS.register(proxy);
		proxy.register(event);
	}

	@EventHandler
	public void secondMovement(FMLInitializationEvent event) {
		proxy.register(event);
	}
}
