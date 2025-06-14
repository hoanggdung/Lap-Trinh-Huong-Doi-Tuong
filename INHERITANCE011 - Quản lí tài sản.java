import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

abstract class Asset {
    protected String name;
    protected double value;
    protected Date acquisitionDate;

    public Asset(String name, double value, Date acquisitionDate) {
        this.name = name;
        this.value = value;
        this.acquisitionDate = acquisitionDate;
    }

    public abstract double getDepreciation();
}

class FixedAsset extends Asset {
    private int usefulLife;

    public FixedAsset(String name, double value, Date acquisitionDate, int usefulLife) {
        super(name, value, acquisitionDate);
        this.usefulLife = usefulLife;
    }

    @Override
    public double getDepreciation() {
        return value / usefulLife;
    }
}

class CurrentAsset extends Asset {
    private double liquidationValue;

    public CurrentAsset(String name, double value, Date acquisitionDate, double liquidationValue) {
        super(name, value, acquisitionDate);
        this.liquidationValue = liquidationValue;
    }

    @Override
    public double getDepreciation() {
        return value * 0.1;
    }
}

class IntangibleAsset extends Asset {
    private int amortizationPeriod;

    public IntangibleAsset(String name, double value, Date acquisitionDate, int amortizationPeriod) {
        super(name, value, acquisitionDate);
        this.amortizationPeriod = amortizationPeriod;
    }

    @Override
    public double getDepreciation() {
        return value / amortizationPeriod;
    }
}

class AssetManager {
    private List<Asset> assets;

    public AssetManager() {
        assets = new ArrayList<>();
    }

    public void addAsset(Asset asset) {
        assets.add(asset);
    }

    public double getTotalValue() {
        double totalValue = 0;
        for (Asset asset : assets) {
            totalValue += asset.value;
        }
        return totalValue;
    }

    public double getTotalDepreciation() {
        double totalDepreciation = 0;
        for (Asset asset : assets) {
            totalDepreciation += asset.getDepreciation();
        }
        return totalDepreciation;
    }

    public void displayAssets() {
        for (Asset asset : assets) {
            System.out.printf("Asset Name: %s\n", asset.name);
            System.out.printf("Asset Value: %.1f\n", asset.value);
            System.out.printf("Depreciation: %.1f\n", asset.getDepreciation());
            System.out.println("---------------------------");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AssetManager assetManager = new AssetManager();

        int n = Integer.parseInt(scanner.nextLine());
        
        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            String assetType = parts[0];
            String name = parts[1];
            double value = Double.parseDouble(parts[2]);
            int thirdParam = Integer.parseInt(parts[3]);

            Date acquisitionDate = new Date(); // Tạo ngày mua mặc định

            Asset asset = null;

            switch (assetType) {
                case "FixedAsset":
                    asset = new FixedAsset(name, value, acquisitionDate, thirdParam);
                    break;
                case "CurrentAsset":
                    asset = new CurrentAsset(name, value, acquisitionDate, thirdParam);
                    break;
                case "IntangibleAsset":
                    asset = new IntangibleAsset(name, value, acquisitionDate, thirdParam);
                    break;
            }

            if (asset != null) {
                assetManager.addAsset(asset);
            }
        }

        assetManager.displayAssets();
        System.out.printf("Total Value of Assets: %.1f\n", assetManager.getTotalValue());
        System.out.printf("Total Depreciation of Assets: %.1f\n", assetManager.getTotalDepreciation());
    }
}