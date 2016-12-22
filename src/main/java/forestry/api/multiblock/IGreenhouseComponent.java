/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.multiblock;

import forestry.api.climate.IClimateControl;
import forestry.api.climate.IClimateControlProvider;
import forestry.api.climate.IClimateSourceProvider;
import forestry.api.climate.IClimatiserDefinition;
import forestry.api.greenhouse.IGreenhouseListener;
import forestry.api.lepidopterology.IButterflyCocoon;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public interface IGreenhouseComponent<T extends IMultiblockLogicGreenhouse> extends IMultiblockComponent {
	@Override
	T getMultiblockLogic();

	interface Listener extends IGreenhouseComponent {
		IGreenhouseListener getGreenhouseListener();
	}

	interface ClimateControl extends IGreenhouseComponent, IClimateControlProvider {

		@Override
		IClimateControl getClimateControl();
	}

	interface Door extends IGreenhouseComponent {
	}

	interface Climatiser extends IGreenhouseComponent, IClimateSourceProvider {
		IClimatiserDefinition getDefinition();
	}

	interface ButterflyHatch extends IGreenhouseComponent {
		NonNullList<ItemStack> addCocoonLoot(IButterflyCocoon cocoon);
	}

	interface Active extends IGreenhouseComponent {
		void updateServer(int tickCount);

		void updateClient(int tickCount);
	}

}
