package com.bartz24.voidislandcontrol;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import com.bartz24.voidislandcontrol.config.ConfigOptions;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.common.Loader;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StartingInvCommand extends CommandBase implements ICommand {
	private final List<String> aliases;

	public StartingInvCommand() {
		aliases = new ArrayList<>();
		aliases.add("startingInv");
		aliases.add("startingInventory");
	}

	@Override
	public List<String> getAliases() {
		return aliases;
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			@Nullable BlockPos targetPos) {
		return Collections.<String> emptyList();
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
		World world = sender.getEntityWorld();
		EntityPlayerMP player = (EntityPlayerMP) world.getPlayerEntityByName(sender.getCommandSenderEntity().getName());

		int inventorySize = player.inventory.getSizeInventory();
		List<String> list = new ArrayList<>();

		for (int i = 0; i < inventorySize; i++) {
			ItemStack stack = player.inventory.getStackInSlot(i);
			if (!stack.isEmpty()) {
				list.add(asString(i, stack));
			}
		}

		if (Loader.isModLoaded(References.BAUBLES)) baublesIntegration(list, player);

		ConfigOptions.islandSettings.startingItems = list.toArray(new String[list.size()]);
		ConfigManager.sync(References.ModID, Config.Type.INSTANCE);

		player.sendMessage(new TextComponentString("Starting Inventory config set!"));
	}

	@Override
	public String getName() {
		return aliases.get(0);
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "";
	}

	private static String asString(int slot, ItemStack stack) {
		String name = Item.REGISTRY.getNameForObject(stack.getItem()).toString();
		int count = stack.getCount();
		int meta = stack.getMetadata();

		NBTTagCompound nbt = stack.hasTagCompound() ? stack.getTagCompound().copy() : null;

		StringBuilder builder = new StringBuilder(slot + "@");
		builder.append(name);
		builder.append(":").append(meta);
		builder.append("*").append(count);

		if (nbt != null) builder.append("#").append(nbt.toString());

		return builder.toString();
	}

	private static void baublesIntegration(List<String> list, EntityPlayerMP player) {
		int invSize = player.inventory.getSizeInventory();
		IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);

		for (int i = invSize; i < invSize + baubles.getSlots(); i++) {
			ItemStack stack = baubles.getStackInSlot(i - invSize);
			if (!stack.isEmpty()) list.add(asString(invSize, stack));
		}
	}
}
