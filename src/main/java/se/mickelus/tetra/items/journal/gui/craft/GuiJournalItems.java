package se.mickelus.tetra.items.journal.gui.craft;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import se.mickelus.mgui.gui.GuiAttachment;
import se.mickelus.mgui.gui.GuiElement;
import se.mickelus.mgui.gui.animation.Applier;
import se.mickelus.mgui.gui.animation.KeyframeAnimation;
import se.mickelus.tetra.items.modular.ItemModular;
import se.mickelus.tetra.items.modular.impl.ModularTwinHeadItem;
import se.mickelus.tetra.items.modular.impl.ModularSwordItem;
import se.mickelus.tetra.items.modular.impl.bow.ModularBowItem;
import se.mickelus.tetra.items.modular.impl.toolbelt.ItemToolbeltModular;

import java.util.function.Consumer;

public class GuiJournalItems extends GuiElement {

    private final GuiJournalItem sword;
    private final GuiJournalItem toolbelt;
    private final GuiJournalItem tools;
    private final GuiJournalItem bows;

    public GuiJournalItems(int x, int y, int width, int height, Consumer<ItemModular> onItemSelect, Consumer<String> onSlotSelect) {
        super(x, y, width, height);


        sword = new GuiJournalItem(-39, 0, ModularSwordItem.instance, I18n.format("journal.craft.sword"),
                () -> onItemSelect.accept(ModularSwordItem.instance), onSlotSelect);
        sword.setAttachment(GuiAttachment.topCenter);
        addChild(sword);

        toolbelt = new GuiJournalItem(1, -40, ItemToolbeltModular.instance, I18n.format("journal.craft.toolbelt"),
                () -> onItemSelect.accept(ItemToolbeltModular.instance), onSlotSelect);
        toolbelt.setAttachment(GuiAttachment.topCenter);
        addChild(toolbelt);

        tools = new GuiJournalItem(41, 0, ModularTwinHeadItem.instance, I18n.format("journal.craft.tool"),
                () -> onItemSelect.accept(ModularTwinHeadItem.instance), onSlotSelect);
        tools.setAttachment(GuiAttachment.topCenter);
        addChild(tools);

        bows = new GuiJournalItem(1, 40, ModularBowItem.instance, I18n.format("journal.craft.bow"),
                () -> onItemSelect.accept(ModularBowItem.instance), onSlotSelect);
        bows.setAttachment(GuiAttachment.topCenter);
        addChild(bows);
    }

    public void animateOpen() {
        new KeyframeAnimation(200, this)
                .applyTo(new Applier.TranslateY(y - 4, y), new Applier.Opacity(0, 1))
                .withDelay(800)
                .start();
    }

    public void animateBack() {
        new KeyframeAnimation(100, this)
                .applyTo(new Applier.TranslateY(y - 4, y), new Applier.Opacity(0, 1))
                .start();
    }

    public void changeItem(Item item) {
        if (item instanceof ModularSwordItem) {
            toolbelt.setVisible(false);
            tools.setVisible(false);
            bows.setVisible(false);

            sword.setVisible(true);
            sword.setSelected(true);
        } else if (item instanceof ItemToolbeltModular) {
            sword.setVisible(false);
            tools.setVisible(false);
            bows.setVisible(false);

            toolbelt.setVisible(true);
            toolbelt.setSelected(true);
        } else if (item instanceof ModularTwinHeadItem) {
            sword.setVisible(false);
            toolbelt.setVisible(false);
            bows.setVisible(false);

            tools.setVisible(true);
            tools.setSelected(true);
        } else if (item instanceof ModularBowItem) {
            sword.setVisible(false);
            toolbelt.setVisible(false);
            tools.setVisible(false);

            bows.setVisible(true);
            bows.setSelected(true);
        } else {
            sword.setSelected(false);
            toolbelt.setSelected(false);
            tools.setSelected(false);
            bows.setSelected(false);

            sword.setVisible(true);
            toolbelt.setVisible(true);
            tools.setVisible(true);
            bows.setVisible(true);
        }
    }
}
