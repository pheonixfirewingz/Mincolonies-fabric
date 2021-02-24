package cf.pheonixfirewingz.minecolonies.colony;

public final class MinecoloniesAPIProxy implements IMinecoloniesAPI
{
	private static MinecoloniesAPIProxy ourInstance = new MinecoloniesAPIProxy();

	private IMinecoloniesAPI apiInstance;

	public static MinecoloniesAPIProxy getInstance()
	{
		return ourInstance;
	}

	private MinecoloniesAPIProxy()
	{
	}

	public void setApiInstance(final IMinecoloniesAPI apiInstance)
	{
		this.apiInstance = apiInstance;
	}

	@Override
	public IColonyManager getColonyManager()
	{
		return apiInstance.getColonyManager();
	}
}
