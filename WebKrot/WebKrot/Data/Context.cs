using Microsoft.EntityFrameworkCore;
using WebKrot.Entities;

namespace WebKrot.Data
{
    public class Context : DbContext
    {
        public Context(DbContextOptions<Context> options) : base (options)
        {
            
        }
        public DbSet<Category> Categories { get; set; }
    }
}
