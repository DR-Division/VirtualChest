package com.division.command;

import com.division.data.DataManager;
import com.division.util.ChestUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChestCommand implements CommandExecutor, TabCompleter {

    private final List<String> tabCompleteList = Arrays.asList("저장", "리로드", "열기", "활성화", "비활성화", "크기조절", "삭제", "추가");
    private final DataManager manager;
    private final JavaPlugin Plugin;

    public ChestCommand(DataManager manager, JavaPlugin Plugin) {
        this.manager = manager;
        this.Plugin = Plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length >= 1 && tabCompleteList.contains(args[0]) && p.isOp()) {
                switch (args[0]) {
                    case "저장":
                        manager.save();
                        p.sendMessage("데이터가 저장되었습니다.");
                        break;
                    case "리로드":
                        manager.load();
                        p.sendMessage("데이터가 로드되었습니다.");
                        break;
                    case "열기":
                        if (args.length >= 2 && manager.isActive() && manager.isLoaded())
                            openChest(p, args[1]);
                        break;
                    case "활성화":
                        if (manager.isActive())
                            p.sendMessage("이미 활성화 되어있습니다.");
                        else {
                            manager.setActive(true);
                            p.sendMessage("가상창고가 활성화 되었습니다.");
                        }
                        break;
                    case "비활성화":
                        if (!manager.isActive())
                            p.sendMessage("이미 비활성화 되어있습니다.");
                        else {
                            manager.setActive(false);
                            p.sendMessage("가상창고가 비활성화 되었습니다.");
                        }
                        break;
                    case "크기조절":
                        if (!manager.isLoaded())
                            p.sendMessage("현재 데이터 로드중입니다..");
                        else if (args.length >= 4 && ChestUtil.parseInt(args[2]) != -1 && ChestUtil.parseInt(args[3]) != -1) {
                            int index = ChestUtil.parseInt(args[2]);
                            int row = ChestUtil.parseInt(args[3]);
                            Player target = Bukkit.getPlayer(args[1]);
                            if (index > 0 && row > 0 && row < 7 && target != null && manager.hasData(target.getUniqueId())) {
                                List<Inventory> inventories = manager.getData(target.getUniqueId());
                                if (inventories.size() > index) {
                                    Inventory inventory = inventories.get(index);
                                    int pastSize = inventory.getSize();
                                    inventories.set(index, ChestUtil.createInventory(row, inventory));
                                    p.sendMessage("크기가 조절되었습니다. 대상 : §b" + args[1] + " §f크기 : " + pastSize + " => " + args[3]);
                                }
                            }
                        }
                        break;
                    case "삭제":
                        if (!manager.isLoaded())
                            p.sendMessage("현재 데이터 로드중입니다..");
                        else if (args.length >= 3 && ChestUtil.parseInt(args[2]) != -1) {
                            int index = ChestUtil.parseInt(args[2]);
                            Player target = Bukkit.getPlayer(args[1]);
                            if (index > 0 && target != null && manager.hasData(target.getUniqueId())) {
                                List<Inventory> inventories = manager.getData(target.getUniqueId());
                                if (inventories.size() > index) {
                                    inventories.remove(index);
                                    p.sendMessage("해당 창고가 제거되었습니다.");
                                }
                            }
                        }
                        break;
                    case "추가":
                        if (!manager.isLoaded())
                            p.sendMessage("현재 데이터 로드중입니다..");
                        else if (args.length >= 2) {
                            Player target = Bukkit.getPlayer(args[1]);
                            if (target != null) {
                                if (!manager.hasData(target.getUniqueId()))
                                    manager.addData(target.getUniqueId(), new ArrayList<>());
                                List<Inventory> inventories = manager.getData(target.getUniqueId());
                                inventories.add(ChestUtil.createInventory(1));
                                p.sendMessage("해당 플레이어의 창고가 생성되었습니다.");
                            }
                        }
                        break;
                }
            }
            else if (p.isOp())
                sendCommandUsage(p);
            else if (manager.isLoaded() && manager.isActive())
                openChest(p, p.getName());
            else
                p.sendMessage("현재 가상창고가 활성화 되어있지 않거나, 데이터를 불러오는 중 입니다.");
            //OPEN CHEST MENU
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return sender.isOp() ? tabCompleteList : null;
    }

    private void openChest(Player p, String target) {
        p.setMetadata("virtual_chest_id", new FixedMetadataValue(Plugin, p.getName())); //virtual_chest_uuid라는 메타데이터에 최종적으로 연 상대의 닉네임을 저장하여 창고 오픈시 사용
        ChestUtil.openChestMenu(manager, p, target);
    }


    public void sendCommandUsage(Player p) {
        p.sendMessage("§f/창고 [저장/리로드/열기 <닉네임>/활성화/비활성화/크기조절 <닉네임> <번호> <1-6>/삭제 <닉네임> <번호>/추가 <닉네임>]");
    }


}
