/*******************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 ******************************************************************************/
package forestry.apiculture.network.packets;

import java.io.IOException;

import forestry.api.multiblock.IMultiblockComponent;
import forestry.core.network.ForestryPacket;
import forestry.core.network.IForestryPacketClient;
import forestry.core.network.IForestryPacketHandlerClient;
import forestry.core.network.PacketBufferForestry;
import forestry.core.network.PacketIdClient;
import forestry.core.proxy.Proxies;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class PacketAlvearyChange extends ForestryPacket implements IForestryPacketClient {
	private final BlockPos controllerPos;

	public PacketAlvearyChange(BlockPos controllerPos) {
		this.controllerPos = controllerPos;
	}

	@Override
	public PacketIdClient getPacketId() {
		return PacketIdClient.ALVERAY_CONTROLLER_CHANGE;
	}

	@Override
	protected void writeData(PacketBufferForestry data) throws IOException {
		data.writeBlockPos(controllerPos);
	}

	public static class Handler implements IForestryPacketHandlerClient {
		@Override
		public void onPacketData(PacketBufferForestry data, EntityPlayer player) throws IOException {
			BlockPos pos = data.readBlockPos();
			TileEntity tile = Proxies.common.getRenderWorld().getTileEntity(pos);
			if (tile instanceof IMultiblockComponent) {
				((IMultiblockComponent) tile).getMultiblockLogic().getController().reassemble();
			}
		}
	}
}
